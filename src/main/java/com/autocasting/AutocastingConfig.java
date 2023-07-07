package com.autocasting;

import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;
import net.runelite.client.config.Range;

import java.awt.*;

@ConfigGroup("autocasting")
public interface AutocastingConfig extends Config
{
	// OVERLAY

	@ConfigSection(
		name = "Overlay",
		description = "Overlay Settings",
		position = 0
	)
	String overlaySettings = "overlay";

	@ConfigItem(
		keyName = "showOverlay",
		name = "Show Overlay",
		description = "Show/hide overlay",
		position = 1,
		section = overlaySettings
	)
	default boolean showOverlay()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showOverlayOutsideCombat",
		name = "Show Outside of Combat",
		description = "Display the autocast overlay panel outside of combat.",
		position = 2,
		section = overlaySettings
	)
	default boolean showOverlayOutsideCombat()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showSpellName",
		name = "Show Spell Name",
		description = "Show/hide spell name",
		position = 3,
		section = overlaySettings
	)
	default boolean showSpellName()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showSpellIcon",
		name = "Show Spell Icon",
		description = "Show/hide spell icon",
		position = 4,
		section = overlaySettings
	)
	default boolean showSpellIcon()
	{
		return true;
	}

	@ConfigItem(
		keyName = "enableOverrideText",
		name = "Enable Override Text",
		description = "Allow custom text to be displayed during alerts.",
		position = 5,
		section = overlaySettings
	)
	default boolean enableOverrideText()
	{
		return true;
	}


	@ConfigItem(
		keyName = "lowMagicLevelText",
		name = "Low Magic Level Override Text",
		description = "Text to override spell name when your magic level drops below the required level. Will not override if left blank.",
		position = 6,
		section = overlaySettings
	)
	default String lowMagicLevelText()
	{
		return "RESTORE";
	}

	@ConfigItem(
		keyName = "noRunesText",
		name = "No Runes Override Text",
		description = "Text to override spell name when you run out of runes for your autocast. Will not override if left blank.",
		position = 7,
		section = overlaySettings
	)
	default String noRunesText()
	{
		return "NO RUNES";
	}

	@ConfigItem(
		keyName = "overlayAlertStyle",
		name = "Overlay Alert Style",
		description = "Configures how the overlay behaves when the autocast is unusable.",
		position = 8,
		section = overlaySettings
	)
	default AutocastingConstants.OverlayNotificationType overlayAlertStyle()
	{
		return AutocastingConstants.OverlayNotificationType.FLASH;
	}


	@Alpha
	@ConfigItem(
		keyName = "overlayAlertColor",
		name = "Overlay Alert Color",
		description = "Color of Autocast overlay when flashing/solid",
		position = 9,
		section = overlaySettings
	)
	default Color overlayAlertColor()
	{
		return AutocastingConstants.RED_FLASH_COLOR;
	}

	@Range(min = AutocastingConstants.MINIMUM_COUNTER_FLASH_PERIOD)
	@ConfigItem(
		keyName = "flashPeriod",
		name = "Overlay Flash Period",
		description = "Period (in frames) at which the Autocast overlay flashes. Lower numbers flash faster.",
		position = 10,
		section = overlaySettings
	)
	default int getFlashPeriod()
	{
		return AutocastingConstants.DEFAULT_COUNTER_FLASH_PERIOD;
	}

	@ConfigItem(
		keyName = "showCastsRemaining",
		name = "Display Casts Remaining",
		description = "Puts the amount of casts of your autocast spell you have left, based on your current runes.",
		position = 11,
		section = overlaySettings
	)
	default boolean showCastsRemaining()
	{
		return true;
	}

	@Range(min = AutocastingConstants.MINIMUM_CAST_RUNES_THRESHOLD, max = AutocastingConstants.MAXIMUM_CAST_RUNES_THRESHOLD)
	@ConfigItem(
		keyName = "castRemainingThreshold",
		name = "Display Cast Limit",
		description = "Max amount of remaining casts which will be displayed. (1-999,999)",
		position = 12,
		section = overlaySettings
	)
	default int displayCastLimit()
	{
		return AutocastingConstants.DEFAULT_CAST_RUNES_THRESHOLD;
	}


	// DESKTOP NOTIFICATIONS

	@ConfigSection(
		name = "Notifications",
		description = "In-Game and Desktop Notification Settings",
		position = 13
	)
	String notificationSettings = "notifications";

	@ConfigItem(
		keyName = "notifyOutOfCombat",
		name = "Notify Out Of Combat",
		description = "Controls if notifications for autocast spells will appear outside of combat.",
		position = 14,
		section = notificationSettings
	)
	default boolean notifyOutOfCombat()
	{
		return true;
	}

	@ConfigItem(
		keyName = "notifyOnStatDrain",
		name = "Stat Drain Notification",
		description = "Notifies you when your magic level falls below the level required for your autocast spell.",
		position = 15,
		section = notificationSettings
	)
	default AutocastingConstants.ChatNotificationType notifyOnStatDrain()
	{
		return AutocastingConstants.ChatNotificationType.BOTH;
	}

	@ConfigItem(
		keyName = "notifyOnLowCasts",
		name = "Low Casts Notification",
		description = "Notifies you when your amount of casts falls to or below the Low Cast Threshold.",
		position = 16,
		section = notificationSettings
	)
	default AutocastingConstants.ChatNotificationType notifyOnLowCasts()
	{
		return AutocastingConstants.ChatNotificationType.BOTH;
	}

	@Range(min = AutocastingConstants.MINIMUM_CAST_RUNES_THRESHOLD, max = AutocastingConstants.MAXIMUM_CAST_RUNES_THRESHOLD)
	@ConfigItem(
		keyName = "lowCastNotificationThreshold",
		name = "Low Cast Threshold",
		description = "Amount of casts to notify you on, based on your current runes.",
		position = 17,
		section = notificationSettings
	)
	default int lowCastNotificationThreshold()
	{
		return AutocastingConstants.DEFAULT_LOW_RUNES_NOTIFICATION_THRESHOLD;
	}


	@ConfigItem(
		keyName = "notifyOnLowCasts",
		name = "No Casts Notification",
		description = "Notifies you when you run out of runes to cast your autocast spell.",
		position = 18,
		section = notificationSettings
	)
	default AutocastingConstants.ChatNotificationType notifyOnNoCasts()
	{
		return AutocastingConstants.ChatNotificationType.BOTH;
	}
}
