package net.runelite.client.plugins.playernotifier.src.main.java.com.playernotifier;

import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

//import java.awt.*;

@ConfigGroup("playernotifier")
public interface PlayerNotifierConfig extends Config
{
	@ConfigItem(
		position = 0,
		keyName = "doNotNotifyList",
		name = "Do Not Notify List:",
		description = "Don't notify for the players listed below. Separate players with commas and put names in quotes. For example, 'Player 1'"
	)
	default String doNotNotifyList()
	{
		return "";
	}

	@Alpha
	@ConfigItem(
			position = 1,
			keyName = "fillColor",
			name = "Fill Color",
			description = "Color of the screen when a player is seen."
	)
	default Color overlayColor()
	{
		return new Color(255, 0, 0, 100);
	}

}
