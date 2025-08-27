package com.autocasting.datatypes;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.api.gameval.ItemID;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public enum Pouch
{

	RUNE_POUCH(ItemID.BH_RUNE_POUCH, PouchType.THREE_SLOTS),
	RUNE_POUCH_L(ItemID.BH_RUNE_POUCH_TROUVER, PouchType.THREE_SLOTS),
	DIVINE_RUNE_POUCH(ItemID.DIVINE_RUNE_POUCH, PouchType.FOUR_SLOTS),
	DIVINE_RUNE_POUCH_L(ItemID.DIVINE_RUNE_POUCH_TROUVER, PouchType.FOUR_SLOTS),
	LMS_POUCH(ItemID.BR_RUNE_REPLACEMENT, PouchType.LMS),
	EMIRS_ARENA_POUCH(ItemID.PVPA_RUNE_REPLACEMENT, PouchType.INFINITE);

	@Getter
	private final int id;

	@Getter
	private final PouchType pouchType;

	private static final Map<Integer, Pouch> POUCH_MAP;

	static
	{
		ImmutableMap.Builder<Integer, Pouch> builder = new ImmutableMap.Builder<>();

		for (Pouch pouch : values())
		{
			builder.put(pouch.getId(), pouch);
		}
		POUCH_MAP = builder.build();
	}

	public static Pouch getPouch(int itemId)
	{
		return POUCH_MAP.getOrDefault(itemId, null);
	}
}
