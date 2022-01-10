package net.runelite.client.plugins.autocastutilities.src.main.java.com.autocastutilities;

import lombok.AllArgsConstructor;
import net.runelite.client.config.*;

import java.awt.*;

@ConfigGroup("autocastutilities")
public interface AutocastUtilitiesConfig extends Config
{
    @AllArgsConstructor
    enum OverlayNotificationType
    {
        FLASH("Flash"),
        SOLID("Solid"),
        NONE("None");

        private final String value;

        @Override
        public String toString()
        {
            return value;
        }
    }

    @ConfigItem(
            keyName = "overlayNotificationType",
            name = "Overlay Notification Type",
            description = "Configures how overlay behaves when magic level low.",
            position = 0
    )
    default AutocastUtilitiesConfig.OverlayNotificationType overlayNotificationType() { return AutocastUtilitiesConfig.OverlayNotificationType.FLASH; }

    @ConfigItem(
            keyName = "showOverlay",
            name = "Show Overlay",
            description = "Show/hide overlay",
            position = 1
    )
    default boolean showOverlay()
    {
        return true;
    }

    @ConfigItem(
            keyName = "showSpellName",
            name = "Show Spell Name",
            description = "Show/hide spell name",
            position = 2
    )
    default boolean showSpellName()
    {
        return true;
    }

    @ConfigItem(
            keyName = "showSpellIcon",
            name = "Show Spell Icon",
            description = "Show/hide spell icon",
            position = 3
    )
    default boolean showSpellIcon() { return true; }

    @Alpha
    @ConfigItem(
            keyName = "overlayFlashColor",
            name = "Overlay Flash Color",
            description = "Color of Autocast overlay when flashing/solid",
            position = 4
    )
    default Color getOverlayColor() { return AutocastOverlay.RED_FLASH_COLOR; }

    @Range(min = AutocastOverlay.MINIMUM_COUNTER_FLASH_PERIOD)
    @ConfigItem(
            keyName = "flashPeriod",
            name = "Overlay Flash Period",
            description = "Period at which the Autocast overlay flashes. Higher # equals slower flash. Lowest # allowed is 1.",
            position = 5
    )
    default int getFlashPeriod()
    {
        return AutocastOverlay.DEFAULT_COUNTER_FLASH_PERIOD;
    }

    @ConfigItem(
            keyName = "sendGameMessage",
            name = "Send Game Message",
            description = "Sends a game message when your magic level falls below the level required for your autocast spell.",
            position = 6
    )
    default boolean sendGameMessage() { return true; }


}
