package net.runelite.client.plugins.autocastutilities.src.main.java.com.autocastutilities;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.StatChanged;
import net.runelite.api.events.VarbitChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.chat.ChatColorType;
import net.runelite.client.chat.ChatMessageBuilder;
import net.runelite.client.chat.ChatMessageManager;
import net.runelite.client.chat.QueuedMessage;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.SpriteManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
//import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
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

	@Getter
	private AutocastSpell currentAutocastSpell;

	private static final String AUTOCAST_UNEQUIP_NOTIFICATION_MESSAGE = "Your magic level has dropped below what is required to autocast your spell.";

//	private AutocastSpellInfoBox autocastSpellInfoBox;

	@Inject
	private AutocastUtilitiesConfig config;

	@Inject
	private AutocastOverlay autocastOverlay;

	@Inject
	private Client client;

//	@Inject
//	private InfoBoxManager infoBoxManager;

	@Inject
	private SpriteManager spriteManager;

	@Inject
	private OverlayManager overlayManager;

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
//		updateAutocastSpellInfoBox();
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
//				currentAutocastSpell = AutocastSpell.NO_SPELL;		// do not want to do this
				// TODO: Create function that shows NO_SPELL on the infobox without changing current spell
				sendChatMessage(AUTOCAST_UNEQUIP_NOTIFICATION_MESSAGE);
			}
			else
			{
				magicLevelMeetsSpellReq = true;
			}
			// For debug
			System.out.println("Skill: " + skill.getName() + ", xp: " + String.valueOf(xp) + ", statLevel: " + String.valueOf(statLevel) + ", boostedLevel: " + String.valueOf(boostedLevel));
			System.out.println("Level Req: " + String.valueOf(autocastSpell.getLevelRequirement()) + " Name: " + String.valueOf(autocastSpell.getName()) + " Varbit: " + String.valueOf(autocastSpell.getAutocastSpellVarClientInt()) + " ID: " + String.valueOf(autocastSpell.getAutocastSpellID()) );
		}
	}

	private void startPlugin()
	{
		if (client.getGameState() == GameState.LOGGED_IN) {
			updateAutocastSpell();
		}
		else
		{
			currentAutocastSpell = AutocastSpell.NO_SPELL;
		}
//		autocastSpellInfoBox = createAutocastSpellInfoBox();
//		infoBoxManager.addInfoBox(autocastSpellInfoBox);
		overlayManager.add(autocastOverlay);
	}

	private void shutdownPlugin()
	{
//		infoBoxManager.removeInfoBox(autocastSpellInfoBox);
		overlayManager.remove(autocastOverlay);
//		autocastSpellInfoBox = null;
	}

	private void updateAutocastSpell() {
		// Get new autocast spell.
		AutocastSpell newAutocastSpell = getAutocastSpellFromClient();
		if (newAutocastSpell == null) { return; }

		// If the new spell is not null, and there is currently no autocast spell selected, update it
		// TODO: The following if else tree can be mostly replaced with "currentAutocastSpell = newAutocastSpell;" for all cases, but
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
		}
	}

	private AutocastSpell getAutocastSpellFromClient()
	{
		// Get new autocast spell.
		int autocastSpellID = client.getVar(Varbits.AUTOCAST_SPELL);
		AutocastSpell newAutocastSpell = AutocastSpell.getAutocastSpell(autocastSpellID);
		return newAutocastSpell;
	}

//	private AutocastSpellInfoBox createAutocastSpellInfoBox() {
//		BufferedImage image = spriteManager.getSprite(SpriteID.SPELL_WIND_STRIKE, 0);
//		return new AutocastSpellInfoBox(image, this, "Wind Strike");
//	}

//	private void updateAutocastSpellInfoBox()
//	{
//		if (currentAutocastSpell == null) { return; }
//		BufferedImage image = spriteManager.getSprite(currentAutocastSpell.getSpriteID(), 0);
//		AutocastSpellInfoBox newInfoBox = new AutocastSpellInfoBox(image, this, currentAutocastSpell.getName());
//		infoBoxManager.removeInfoBox(autocastSpellInfoBox);
//		autocastSpellInfoBox = newInfoBox;
//		infoBoxManager.addInfoBox(autocastSpellInfoBox);
//	}

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

	public BufferedImage getImage(int spriteID)
	{
		return spriteManager.getSprite(spriteID, 0);
	}

	@Provides
	AutocastUtilitiesConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(AutocastUtilitiesConfig.class);
	}
}
