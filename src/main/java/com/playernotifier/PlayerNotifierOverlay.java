package net.runelite.client.plugins.playernotifier.src.main.java.com.playernotifier;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

import javax.inject.Inject;
import java.awt.*;

class PlayerNotifierOverlay extends Overlay
{
	private final Client client;
	private final PlayerNotifierPlugin plugin;
	private final PlayerNotifierConfig config;

	@Inject
	private PlayerNotifierOverlay(Client client, PlayerNotifierPlugin plugin, PlayerNotifierConfig config)
	{
		super(plugin);
		setPosition(OverlayPosition.DYNAMIC);
		setLayer(OverlayLayer.ABOVE_SCENE);
		this.client = client;
		this.plugin = plugin;
		this.config = config;
	}

	@Override
	public Dimension render(Graphics2D graphics) {
		if (plugin.shouldRenderOverlay()) {
//			if(config.flash() && client.getGameCycle() % 40 >= 20) return null;
			Color color = graphics.getColor();
			graphics.setColor(config.overlayColor());
			graphics.fill(new Rectangle(client.getCanvas().getSize()));
			graphics.setColor(color);

		}
		return null;
	}
}
