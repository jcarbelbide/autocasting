package net.runelite.client.plugins.autocastutilities.src.main.java.com.autocastutilities;

import com.google.inject.Inject;
import net.runelite.api.Client;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.components.ComponentOrientation;
import net.runelite.client.ui.overlay.components.ImageComponent;
import net.runelite.client.ui.overlay.components.LineComponent;
import net.runelite.client.ui.overlay.components.SplitComponent;
import net.runelite.client.ui.overlay.components.TextComponent;
import net.runelite.client.ui.overlay.components.TitleComponent;

import java.awt.*;
import java.awt.image.BufferedImage;

class AutocastOverlay extends OverlayPanel {

    private static final int SPELL_NAME_ICON_GAP = 4;

    private final Client client;
    private final AutocastUtilitiesPlugin plugin;
    private final AutocastUtilitiesConfig config;

    @Inject
    AutocastOverlay(Client client, AutocastUtilitiesPlugin plugin, AutocastUtilitiesConfig config)
    {
        super(plugin);
        this.client = client;
        this.plugin = plugin;
        this.config = config;
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        String title = "Autocasting";
        super.panelComponent.getChildren().add(
                TitleComponent.builder()
                        .text(title)
                        .build());

        LineComponent spellNameComponent = LineComponent.builder()
                .left(getCurrentSpellName())
                .build();
        ImageComponent spellImageComponent = new ImageComponent(getCurrentSpellImage());
        SplitComponent spellNameIcon = SplitComponent.builder()
                .first(spellNameComponent)
                .second(spellImageComponent)
                .orientation(ComponentOrientation.HORIZONTAL)
                .gap(new Point(SPELL_NAME_ICON_GAP, 0))
                .build();

        super.panelComponent.getChildren().add(
                spellNameIcon);


        return super.render(graphics);
    }

    private BufferedImage getCurrentSpellImage()
    {
        return plugin.getImage(plugin.getCurrentAutocastSpell().getSpriteID());
    }

    private String getCurrentSpellName()
    {
        return plugin.getCurrentAutocastSpell().getName();
    }
}