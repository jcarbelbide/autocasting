package net.runelite.client.plugins.autocastutilities.src.main.java.com.autocastutilities;

import lombok.AllArgsConstructor;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.plugins.discord.DiscordConfig;

@ConfigGroup("autocastutilities")
public interface AutocastUtilitiesConfig extends Config
{
    @AllArgsConstructor
    enum OverlayNotificationType
    {
        FLASH("Flash"),
        SOLID("Solid");

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
            keyName = "greeting",
            name = "Welcome Greeting",
            description = "The message to show to the user when they login",
            position = 1
    )
    default String greeting()
    {
        return "Hello";
    }


}
