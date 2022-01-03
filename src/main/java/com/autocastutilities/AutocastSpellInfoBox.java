package net.runelite.client.plugins.autocastutilities.src.main.java.com.autocastutilities;

import lombok.Setter;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.infobox.InfoBox;
import javax.annotation.Nonnull;
import java.awt.*;
import java.awt.image.BufferedImage;


public class AutocastSpellInfoBox extends InfoBox
{
    @Setter
    private String spellName;

    public AutocastSpellInfoBox(BufferedImage image, @Nonnull Plugin plugin, String spellName) {
        super(image, plugin);
        this.spellName = spellName;
    }

    @Override
    public String getText()
    {
        return spellName;
    }

    @Override
    public Color getTextColor()
    {
        return Color.WHITE;
    }

    @Override
    public boolean render()
    {
        // TODO: Check if this renders only when the config is activated. If so, no need to check for it.
        return true;
    }

    @Override
    public boolean cull()
    {
        // TODO: Find out what this means.
        return false;
    }

}
