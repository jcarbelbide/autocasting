package net.runelite.client.plugins.autocastutilities.src.main.java.com.autocastutilities;

import com.google.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.TitleComponent;

import java.awt.*;

class AutocastOverlay extends OverlayPanel {

    private static final int PANEL_WIDTH_OFFSET = 10; // assumes 8 for panel component border + 2px between left and right

    private final Client client;
    private final AutocastUtilitiesPlugin plugin;
    private final AutocastUtilitiesConfig config;

    @Inject
    AutocastOverlay(Client client, AutocastUtilitiesPlugin plugin, AutocastUtilitiesConfig config)
    {
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
        this.client = client;
        this.plugin = plugin;
        this.config = config;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        String title = "Autocasting";
        panelComponent.getChildren().add(
                TitleComponent.builder()
                        .text(title)
                        .build());

        return super.render(graphics);
    }
}