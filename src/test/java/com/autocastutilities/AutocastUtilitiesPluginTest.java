package com.autocastutilities;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;
import net.runelite.client.plugins.autocastutilities.src.main.java.com.autocastutilities.AutocastUtilitiesPlugin;

public class AutocastUtilitiesPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(AutocastUtilitiesPlugin.class);
		RuneLite.main(args);
	}
}
