package net.runelite.client.plugins.playernotifier.src.test.java.com.playernotifier;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;
import net.runelite.client.plugins.playernotifier.src.main.java.com.playernotifier.PlayerNotifierPlugin;

public class PlayerNotifierPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(PlayerNotifierPlugin.class);
		RuneLite.main(args);
	}
}