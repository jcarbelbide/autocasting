package net.runelite.client.plugins.autocastutilities.src.main.java.com.autocastutilities;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.StatChanged;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.SpriteManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import java.awt.image.BufferedImage;

/*
TODO: Work on Overlay class to replace infobox class
TODO: Add remaining spells to AutocastSpells enum.
 */

@Slf4j
@PluginDescriptor(
	name = "Autocast Utilities",
	description = "Notifies client when certain skills fall under a threshold.",
	tags = {"skill", "notifier", "notifications", "mage", "magic", "reduced", "reduction", "level", "drain", "autocast", "cast"}
)
public class AutocastUtilitiesPlugin extends Plugin
{
	@Getter
	private boolean magicLevelMeetsSpellReq;

	private AutocastSpell currentAutocastSpell;

	private static final String AUTOCAST_UNEQUIP_NOTIFICATION_MESSAGE = "Your magic level has dropped below what is required to autocast your spell.";

	private AutocastSpellInfoBox autocastSpellInfoBox;

	@Inject
	private Client client;

	@Inject
	private AutocastUtilitiesConfig config;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private SpriteManager spriteManager;

	@Inject
	private ItemManager itemManager;

	@Inject
	private ClientThread clientThread;

	@Inject
	private ChatMessageManager chatMessageManager;

	@Override
	protected void startUp() throws Exception
	{
		log.info("AutocastUtilities started!");
		clientThread.invoke(this::startPlugin);
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("AutocastUtilities stopped!");
		clientThread.invoke(this::shutdownPlugin);
	}

	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{
		updateAutocastSpell();
		updateAutocastSpellInfoBox();
	}

	@Subscribe
	public void onStatChanged(StatChanged event)
	{
		Skill skill = event.getSkill();
		int xp = event.getXp();
		int statLevel = event.getLevel();
		int boostedLevel = event.getBoostedLevel();

		if (skill.getName().equals(Skill.MAGIC.getName()))
		{
			// Now need to check if new boostedLevel is still high enough for the autocast spell
			int autocastSpellID = client.getVar(Varbits.AUTOCAST_SPELL);
			AutocastSpell autocastSpell = AutocastSpell.getAutocastSpell(autocastSpellID);
			if (boostedLevel < autocastSpell.getLevelRequirement())
			{
				magicLevelMeetsSpellReq = false;
				System.out.println("Level too low to autocast.");
				currentAutocastSpell = AutocastSpell.NOSPELL;
				updateAutocastSpellInfoBox();
			}
			else
			{
				magicLevelMeetsSpellReq = true;
				System.out.println("Magic level high enough for autocast");
			}
			// For debug
			System.out.println("Skill: " + skill.getName() + ", xp: " + String.valueOf(xp) + ", statLevel: " + String.valueOf(statLevel) + ", boostedLevel: " + String.valueOf(boostedLevel));
			System.out.println("Level Req: " + String.valueOf(autocastSpell.getLevelRequirement()) + " Name: " + String.valueOf(autocastSpell.getName()) + " Varbit: " + String.valueOf(autocastSpell.getAutocastSpellVarClientInt()) + " ID: " + String.valueOf(autocastSpell.getAutocastSpellID()) );
		}
	}

	private void startPlugin()
	{
		updateAutocastSpell();
		autocastSpellInfoBox = createAutocastSpellInfoBox();
		infoBoxManager.addInfoBox(autocastSpellInfoBox);
	}

	private void shutdownPlugin()
	{
		infoBoxManager.removeInfoBox(autocastSpellInfoBox);
		autocastSpellInfoBox = null;
	}

	private void updateAutocastSpell() {
		// Get new autocast spell.
		AutocastSpell newAutocastSpell = getAutocastSpellFromClient();
		if (newAutocastSpell == null) { return; }

//		System.out.println(newAutocastSpell.getAutocastSpellID() + ", " + currentAutocastSpell.getAutocastSpellID());
		// If the new spell is not null, and there is currently no autocast spell selected, update it
		// TODO: The following if else tree can be replaced with "currentAutocastSpell = newAutocastSpell;" for all cases, but
		// 		but I'm not sure how expensive that can add up to be with varbits changing all the time. Maybe it's better
		// 		than checking these conditions every time, i dont know. Someone with more knowledge, please let me know.
		if (currentAutocastSpell == null)
		{
			currentAutocastSpell = newAutocastSpell;
		}
		else if (newAutocastSpell.getAutocastSpellID() == currentAutocastSpell.getAutocastSpellID())
		{
			// No change to autocast spell
			return;
		}
		else
		{
			// Otherwise, update the spell.
			currentAutocastSpell = newAutocastSpell;
			System.out.println("Autocast spell change detected. New spell: " + currentAutocastSpell.getName());
			sendChatMessage(AUTOCAST_UNEQUIP_NOTIFICATION_MESSAGE);
		}
	}

	private AutocastSpell getAutocastSpellFromClient()
	{
		// Get new autocast spell.
		int autocastSpellID = client.getVar(Varbits.AUTOCAST_SPELL);
		AutocastSpell newAutocastSpell = AutocastSpell.getAutocastSpell(autocastSpellID);
		return newAutocastSpell;
	}

	private AutocastSpellInfoBox createAutocastSpellInfoBox() {
		BufferedImage image = spriteManager.getSprite(SpriteID.SPELL_WIND_STRIKE, 0);
		return new AutocastSpellInfoBox(image, this, "Wind Strike");
	}

	private void updateAutocastSpellInfoBox()
	{
		if (currentAutocastSpell == null) { return; }
		BufferedImage image = spriteManager.getSprite(currentAutocastSpell.getSpriteID(), 0);
		AutocastSpellInfoBox newInfoBox = new AutocastSpellInfoBox(image, this, currentAutocastSpell.getName());
		infoBoxManager.removeInfoBox(autocastSpellInfoBox);
		autocastSpellInfoBox = newInfoBox;
		infoBoxManager.addInfoBox(autocastSpellInfoBox);
	}

	// Borrowed from DailyTasksPlugin.java
	private void sendChatMessage(String chatMessage)
	{
		final String message = new ChatMessageBuilder()
				.append(ChatColorType.HIGHLIGHT)
				.append(chatMessage)
				.build();

		chatMessageManager.queue(
				QueuedMessage.builder()
						.type(ChatMessageType.CONSOLE)
						.runeLiteFormattedMessage(message)
						.build());
	}

	@Provides
	AutocastUtilitiesConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(AutocastUtilitiesConfig.class);
	}
}
