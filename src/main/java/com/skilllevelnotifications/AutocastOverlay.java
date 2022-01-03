package net.runelite.client.plugins.skilllevelnotifications.src.main.java.com.skilllevelnotifications;

import com.google.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.game.ItemManager;
import net.runelite.client.plugins.runecraft.RunecraftConfig;
import net.runelite.client.plugins.runecraft.RunecraftPlugin;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.TitleComponent;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

class AutocastOverlay extends OverlayPanel {

    private static final int PANEL_WIDTH_OFFSET = 10; // assumes 8 for panel component border + 2px between left and right

    private final Client client;
    private final SkillLevelNotificationsPlugin plugin;
    private final SkillLevelNotificationsConfig config;

    @Inject
    AutocastOverlay(Client client, SkillLevelNotificationsPlugin plugin, SkillLevelNotificationsConfig config)
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