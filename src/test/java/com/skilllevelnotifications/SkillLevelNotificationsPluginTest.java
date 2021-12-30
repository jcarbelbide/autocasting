package net.runelite.client.plugins.skilllevelnotifications.src.test.java.com.skilllevelnotifications;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;
import net.runelite.client.plugins.skilllevelnotifications.src.main.java.com.skilllevelnotifications.SkillLevelNotificationsPlugin;

public class SkillLevelNotificationsPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(SkillLevelNotificationsPlugin.class);
		RuneLite.main(args);
	}
}