package com.autocasting;

import com.autocasting.datatypes.Spell;
import com.google.inject.Provides;
import javax.inject.Inject;

import net.runelite.api.*;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

/*
TODO: Add remaining spells to AutocastingSpells enum.
TODO: Only show overlay when player is equipping a staff or other autocastable weapon.
*/

@PluginDescriptor(
	name = "Autocasting",
	description = "Notifies client when magic level falls below required level for spell.",
	tags = {"notifier", "notifications", "mage", "magic", "reduced", "reduction", "level", "drain", "autocast", "autocasting", "cast", "utilities"}
)
public class AutocastingPlugin extends Plugin
{
	@Inject
	private AutocastingState state;

	@Inject
	private AutocastingOverlay autocastOverlay;

	@Inject
	private AutocastingSubscriptions subscriptions;

	@Inject
	private Client client;

	@Inject
	private OverlayManager overlayManager;

	@Inject
	private ClientThread clientThread;

	@Inject
	private EventBus eventBus;

	private boolean isInitialized = false;


	@Override
	protected void startUp() throws Exception
	{
		if (!isInitialized)
		{
			eventBus.register(subscriptions);
			isInitialized = true;
		}
		clientThread.invoke(this::startPlugin);
		overlayManager.add(autocastOverlay);
	}

	@Override
	protected void shutDown() throws Exception
	{
		overlayManager.remove(autocastOverlay);
	}

	private void startPlugin()
	{
		if (client.getGameState() == GameState.LOGGED_IN)
		{
			state.updateAutocastSpell();
		}
		else
		{
			state.setCurrentAutocastSpell(Spell.NO_SPELL);
		}
	}

	@Provides
	AutocastingConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(AutocastingConfig.class);
	}
}