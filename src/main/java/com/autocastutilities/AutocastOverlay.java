package net.runelite.client.plugins.autocastutilities.src.main.java.com.autocastutilities;

import com.google.inject.Inject;
import net.runelite.client.ui.overlay.OverlayPanel;
import net.runelite.client.ui.overlay.components.*;
import net.runelite.client.ui.overlay.components.ComponentOrientation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

class AutocastOverlay extends OverlayPanel
{
    private int counter = 0;
    private final int COUNTER_FLASH_PERIOD = 40;
    private final Color RED_FLASH_COLOR = new Color(140, 55, 0, 255);

    private final AutocastUtilitiesPlugin plugin;
    private final AutocastUtilitiesConfig config;

    @Inject
    AutocastOverlay(AutocastUtilitiesPlugin plugin, AutocastUtilitiesConfig config)
    {
        super(plugin);
        this.plugin = plugin;
        this.config = config;
        super.setDynamicFont(true);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
//        String title = "Autocasting";
//        panelComponent.getChildren().add(
//                TitleComponent.builder()
//                        .text(title)
//                        .build());

        TitleComponent spellNameComponent = TitleComponent.builder()
                .text(getCurrentSpellName())
                .build();
        ImageComponent spellImageComponent = new ImageComponent(getCurrentSpellImage());

        SplitComponent spellNameIcon = SplitComponent.builder()
                .first(spellNameComponent)
                .second(spellImageComponent)
                .orientation(ComponentOrientation.HORIZONTAL)
                .build();

        panelComponent.getChildren().add(
                spellNameIcon);

        panelComponent.setPreferredSize(new Dimension(
                graphics.getFontMetrics().stringWidth(getCurrentSpellName()) + 10,
                0));

        configureBackground();

        return super.render(graphics);
    }

    private void configureBackground()
    {
        if (config.overlayNotificationType() == AutocastUtilitiesConfig.OverlayNotificationType.FLASH)
        {
            flashBackground();
        }
        else
        {
            solidBackground();
        }
    }

    private void flashBackground()
    {
        if (plugin.isMagicLevelTooLowForSpell() && (++counter % COUNTER_FLASH_PERIOD > COUNTER_FLASH_PERIOD / 2))
        {
            panelComponent.setBackgroundColor(RED_FLASH_COLOR);
        }
        else
        {
            panelComponent.setBackgroundColor(ComponentConstants.STANDARD_BACKGROUND_COLOR);
        }
    }

    private void solidBackground()
    {
        if (plugin.isMagicLevelTooLowForSpell())
        {
            panelComponent.setBackgroundColor(RED_FLASH_COLOR);
        }
        else
        {
            panelComponent.setBackgroundColor(ComponentConstants.STANDARD_BACKGROUND_COLOR);
        }
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