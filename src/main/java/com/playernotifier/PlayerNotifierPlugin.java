package net.runelite.client.plugins.playernotifier.src.main.java.com.playernotifier;

import com.google.inject.Provides;
import javax.inject.Inject;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.Player;
import net.runelite.api.events.GameStateChanged;
import net.runelite.api.events.PlayerSpawned;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.PlayerDespawned;

import java.util.List;
import java.util.HashSet;
import java.util.stream.Collectors;
import net.runelite.client.ui.overlay.OverlayManager;
import org.apache.commons.lang3.ObjectUtils;

@Slf4j
@PluginDescriptor(
	name = "PlayerNotifier",
	description = "Notifies client when another player has entered the scene",
	tags = {"player", "notifier", "pk", "wilderness", "wildy"}
)
public class PlayerNotifierPlugin extends Plugin
{
	private String clientPlayerName;
	private List<Player> nearbyPlayers;
	private HashSet<String> doNotNotifySet;

	@Inject
	private Client client;

	@Inject
	private PlayerNotifierConfig config;

	@Inject
	private PlayerNotifierOverlay overlay;

	@Inject
	private OverlayManager overlayManager;

	@Provides
	PlayerNotifierConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(PlayerNotifierConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		createDoNotNotifyHashSet();
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(overlay);
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
			clientPlayerName = client.getUsername();
			createDoNotNotifyHashSet();
		}
	}

	@Subscribe
	public void onGameTick(GameTick gameTick)
	{
		nearbyPlayers = client.getPlayers();

	}

	private void createDoNotNotifyHashSet()
	{
		doNotNotifySet = new HashSet<>();
		if (clientPlayerName != null)
		{
			doNotNotifySet.add(clientPlayerName.toLowerCase());
		}
		for (String playerName : config.doNotNotifyList().split(","))
		{
			doNotNotifySet.add(playerName.toLowerCase().replaceAll("\n", ""));
		}
		System.out.println("createDoNotNotifyHashSet: " + doNotNotifySet.toString());
	}

	public boolean shouldRenderOverlay()
	{
		// TODO: Add all players on screen to the okay list

		if (nearbyPlayers == null) { return false; }

		for (Player player : nearbyPlayers) {
			if (player.getName() != null && !doNotNotifySet.contains(player.getName().toLowerCase())) {
				System.out.println("shouldRenderOverlay:" + player.getName() + " was not in the block list.");
				System.out.println("shouldRenderOverlay: HashSet: " + doNotNotifySet.toString());
				return true;
			}
		}
		return false;
	}
}
