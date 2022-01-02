package net.runelite.client.plugins.skilllevelnotifications.src.main.java.com.skilllevelnotifications;

import com.google.inject.Provides;
import javax.inject.Inject;
import javax.swing.*;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.*;
import net.runelite.api.events.StatChanged;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.game.ItemManager;
import net.runelite.client.game.SpriteManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.infobox.InfoBoxManager;
import java.awt.image.BufferedImage;

/*
TODO: Add overlay that displays skill name and then flashes when skill too low
TODO: Add remaining spells to AutocastSpells enum.
TODO: On startup, should initialize, getting the autocasted spell and current mage level.
TODO: See if updating the infobox automatically updates the text. It looks like the image does need to be updated.
 */

@Slf4j
@PluginDescriptor(
	name = "Skill Level Notifications",
	description = "Notifies client when certain skills fall under a threshold.",
	tags = {"skill", "notifier", "notifications", "mage", "magic", "reduced", "reduction", "level", "drain", "autocast", "cast"}
)
public class SkillLevelNotificationsPlugin extends Plugin
{
	@Getter
	private boolean magicLevelMeetsSpellReq;

	@Inject
	private Client client;

	@Inject
	private SkillLevelNotificationsConfig config;

	@Inject
	private InfoBoxManager infoBoxManager;

	@Inject
	private SpriteManager spriteManager;

	@Inject
	private ItemManager itemManager;

	@Inject
	private ClientThread clientThread;

	private AutocastSpellInfoBox autocastSpellInfoBox;

	@Override
	protected void startUp() throws Exception
	{
		log.info("SkillLevelNotificationsPlugin started!");
		clientThread.invoke(this::initInfoBox);
		if (client.getGameState() == GameState.LOGGED_IN)
		{
		}
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("SkillLevelNotificationsPlugin stopped!");
		if (client.getGameState() == GameState.LOGGED_IN)
		{
			infoBoxManager.removeInfoBox(autocastSpellInfoBox);
			autocastSpellInfoBox = null;
		}
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

	private void initInfoBox()
	{
		autocastSpellInfoBox = createAutocastSpellInfoBox();
		infoBoxManager.addInfoBox(autocastSpellInfoBox);
	}

	private AutocastSpellInfoBox createAutocastSpellInfoBox() {
		BufferedImage image = spriteManager.getSprite(SpriteID.SPELL_WIND_STRIKE, 0);
		return new AutocastSpellInfoBox(image, this, "Wind Strike");

	}

	@Provides
	SkillLevelNotificationsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SkillLevelNotificationsConfig.class);
	}
}
