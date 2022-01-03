package net.runelite.client.plugins.autocastutilities.src.main.java.com.autocastutilities;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("autocastutilities")
public interface AutocastUtilitiesConfig extends Config
{
    @ConfigItem(
            keyName = "greeting",
            name = "Welcome Greeting",
            description = "The message to show to the user when they login"
    )
    default String greeting()
    {
        return "Hello";
    }
}
