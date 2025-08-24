package com.autocasting;

import lombok.AllArgsConstructor;
import net.runelite.api.gameval.VarbitID;
import net.runelite.api.gameval.ItemID;

import java.awt.*;

public final class AutocastingConstants
{
	// Config defaults
	public static final int DEFAULT_COUNTER_FLASH_PERIOD = 60;
	public static final int MINIMUM_COUNTER_FLASH_PERIOD = 1;
	public static final int DEFAULT_CAST_RUNES_THRESHOLD = 1000;
	public static final int MINIMUM_CAST_RUNES_THRESHOLD = 1;
	public static final int MAXIMUM_CAST_RUNES_THRESHOLD = 999999;
	public static final int DEFAULT_LOW_RUNES_NOTIFICATION_THRESHOLD = 100;
	public static final Color RED_FLASH_COLOR = new Color(255, 0, 0, 186);

	// Varbits
	public static final int[] VARBIT_RUNE_POUCH_RUNES = {
		VarbitID.RUNE_POUCH_TYPE_1,
		VarbitID.RUNE_POUCH_TYPE_2,
		VarbitID.RUNE_POUCH_TYPE_3,
		VarbitID.RUNE_POUCH_TYPE_4,
	};
	public static final int[] VARBIT_RUNE_POUCH_AMOUNTS = {
		VarbitID.RUNE_POUCH_QUANTITY_1,
		VarbitID.RUNE_POUCH_QUANTITY_2,
		VarbitID.RUNE_POUCH_QUANTITY_3,
		VarbitID.RUNE_POUCH_QUANTITY_4,
	};

	public static final int[] BLACKLISTED_WEAPONS = {
		ItemID.BLISTERWOOD_FLAIL,
		ItemID.SOS_SKULL_SCEPTRE,
		ItemID.SOS_SKULL_SCEPTRE_IMBUED,
		ItemID.SARADOMIN_STAFF,
		ItemID.GUTHIX_STAFF,
		ItemID.ZAMORAK_STAFF
	};

	// Message constants
	public static final String STAT_DRAIN_FORMAT = "Your Magic level has dropped too low to autocast %s!";
	public static final String NO_CASTS_FORMAT = "Out of runes to autocast %s!";
	public static final String LOW_CASTS_FORMAT = "%s casts of %s remaining.";
	public static final String AUOTCAST_NOT_SELECTED_MESSAGE = "Autocast spell not selected!";

	// Other constants
	public static final int OUT_OF_COMBAT_TICK_DELAY = 2;

	@AllArgsConstructor
	public enum OverlayNotificationType
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

	@AllArgsConstructor
	public enum ChatNotificationType
	{
		GAME("Game"),
		BOTH("All"),
		NONE("None");

		private final String value;

		@Override
		public String toString()
		{
			return value;
		}
	}
}
