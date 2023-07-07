package com.autocasting;

import lombok.AllArgsConstructor;
import net.runelite.api.Varbits;

import java.awt.*;

public final class AutocastingConstants
{
	// Config defaults
	public static final int DEFAULT_COUNTER_FLASH_PERIOD = 40;
	public static final int MINIMUM_COUNTER_FLASH_PERIOD = 1;
	public static final int DEFAULT_CAST_RUNES_THRESHOLD = 1000;
	public static final int MINIMUM_CAST_RUNES_THRESHOLD = 1;
	public static final int MAXIMUM_CAST_RUNES_THRESHOLD = 999999;
	public static final int DEFAULT_LOW_RUNES_NOTIFICATION_THRESHOLD = 100;
	public static final Color RED_FLASH_COLOR = new Color(255, 0, 0, 186);

	// Varbits
	public static final int VARBIT_AUTOCAST_SPELL = 276;
	public static final int VARBIT_FOUNTAIN_OF_RUNES = 4145;
	public static final int[] VARBIT_RUNE_POUCH_RUNES = {
		Varbits.RUNE_POUCH_RUNE1,
		Varbits.RUNE_POUCH_RUNE2,
		Varbits.RUNE_POUCH_RUNE3,
		Varbits.RUNE_POUCH_RUNE4
	};
	public static final int[] VARBIT_RUNE_POUCH_AMOUNTS = {
		Varbits.RUNE_POUCH_AMOUNT1,
		Varbits.RUNE_POUCH_AMOUNT2,
		Varbits.RUNE_POUCH_AMOUNT3,
		Varbits.RUNE_POUCH_AMOUNT4
	};

	// Message constants
	public static final String STAT_DRAIN_FORMAT = "Your Magic level has dropped too low to autocast %s!";
	public static final String NO_CASTS_FORMAT = "Out of runes to autocast %s!";
	public static final String LOW_CASTS_FORMAT = "%s casts of %s remaining.";

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
