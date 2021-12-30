package net.runelite.client.plugins.skilllevelnotifications.src.main.java.com.skilllevelnotifications;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

/*
TODO: When skills lowered, check to see if the skill lowered enough that it would unequip the autocasted spell
TODO: Create enum for spells and their magic level.
TODO: On startup, should initialize, getting the autocasted spell and current mage level.
 */

@Slf4j
@PluginDescriptor(
	name = "Skill Level Notifications",
	description = "Notifies client when certain skills fall under a threshold.",
	tags = {"skill", "notifier", "notifications", "mage", "magic", "reduced", "reduction", "level", "drain", "autocast", "cast"}
)
public class SkillLevelNotificationsPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private SkillLevelNotificationsConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.info("SkillLevelNotificationsPlugin started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.info("SkillLevelNotificationsPlugin stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			client.addChatMessage(ChatMessageType.GAMEMESSAGE, "", "Example says " + config.greeting(), null);
		}
	}

	@Provides
	SkillLevelNotificationsConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(SkillLevelNotificationsConfig.class);
	}
}
