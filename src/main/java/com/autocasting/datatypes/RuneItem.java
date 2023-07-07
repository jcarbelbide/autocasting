package com.autocasting.datatypes;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import net.runelite.api.ItemID;

import java.util.Map;

/**
 * Represents a physical rune, like you could have in your inventory.
 * May correspond to one or more rune types (i.e. combination runes)
 */
@RequiredArgsConstructor
@Getter
public enum RuneItem
{
	AIR_RUNE(ItemID.AIR_RUNE, new RuneType[]{RuneType.AIR}),
	AIR_RUNE_6422(ItemID.AIR_RUNE_6422, new RuneType[]{RuneType.AIR}),
	AIR_RUNE_7558(ItemID.AIR_RUNE_7558, new RuneType[]{RuneType.AIR}),
	AIR_RUNE_9693(ItemID.AIR_RUNE_9693, new RuneType[]{RuneType.AIR}),
	AIR_RUNE_11688(ItemID.AIR_RUNE_11688, new RuneType[]{RuneType.AIR}),
	AIR_RUNE_NZ(ItemID.AIR_RUNE_NZ, new RuneType[]{RuneType.AIR}),

	WATER_RUNE(ItemID.WATER_RUNE, new RuneType[]{RuneType.WATER}),
	WATER_RUNE_6424(ItemID.WATER_RUNE_6424, new RuneType[]{RuneType.WATER}),
	WATER_RUNE_7556(ItemID.WATER_RUNE_7556, new RuneType[]{RuneType.WATER}),
	WATER_RUNE_9691(ItemID.WATER_RUNE_9691, new RuneType[]{RuneType.WATER}),
	WATER_RUNE_11687(ItemID.WATER_RUNE_11687, new RuneType[]{RuneType.WATER}),
	WATER_RUNE_NZ(ItemID.WATER_RUNE_NZ, new RuneType[]{RuneType.WATER}),

	EARTH_RUNE(ItemID.EARTH_RUNE, new RuneType[]{RuneType.EARTH}),
	EARTH_RUNE_6426(ItemID.EARTH_RUNE_6426, new RuneType[]{RuneType.EARTH}),
	EARTH_RUNE_9695(ItemID.EARTH_RUNE_9695, new RuneType[]{RuneType.EARTH}),
	EARTH_RUNE_11689(ItemID.EARTH_RUNE_11689, new RuneType[]{RuneType.EARTH}),
	EARTH_RUNE_NZ(ItemID.EARTH_RUNE_NZ, new RuneType[]{RuneType.EARTH}),

	FIRE_RUNE(ItemID.FIRE_RUNE, new RuneType[]{RuneType.FIRE}),
	FIRE_RUNE_6428(ItemID.FIRE_RUNE_6428, new RuneType[]{RuneType.FIRE}),
	FIRE_RUNE_7554(ItemID.FIRE_RUNE_7554, new RuneType[]{RuneType.FIRE}),
	FIRE_RUNE_9699(ItemID.FIRE_RUNE_9699, new RuneType[]{RuneType.FIRE}),
	FIRE_RUNE_11686(ItemID.FIRE_RUNE_11686, new RuneType[]{RuneType.FIRE}),
	FIRE_RUNE_NZ(ItemID.FIRE_RUNE_NZ, new RuneType[]{RuneType.FIRE}),

	MIND_RUNE(ItemID.MIND_RUNE, new RuneType[]{RuneType.MIND}),
	MIND_RUNE_6436(ItemID.MIND_RUNE_6436, new RuneType[]{RuneType.MIND}),
	MIND_RUNE_9697(ItemID.MIND_RUNE_9697, new RuneType[]{RuneType.MIND}),
	MIND_RUNE_11690(ItemID.MIND_RUNE_11690, new RuneType[]{RuneType.MIND}),

	BODY_RUNE(ItemID.BODY_RUNE, new RuneType[]{RuneType.BODY}),
	BODY_RUNE_6438(ItemID.BODY_RUNE_6438, new RuneType[]{RuneType.BODY}),
	BODY_RUNE_11691(ItemID.BODY_RUNE_11691, new RuneType[]{RuneType.BODY}),

	COSMIC_RUNE(ItemID.COSMIC_RUNE, new RuneType[]{RuneType.COSMIC}),
	COSMIC_RUNE_11696(ItemID.COSMIC_RUNE_11696, new RuneType[]{RuneType.COSMIC}),

	CHAOS_RUNE(ItemID.CHAOS_RUNE, new RuneType[]{RuneType.CHAOS}),
	CHAOS_RUNE_6430(ItemID.CHAOS_RUNE_6430, new RuneType[]{RuneType.CHAOS}),
	CHAOS_RUNE_7560(ItemID.CHAOS_RUNE_7560, new RuneType[]{RuneType.CHAOS}),
	CHAOS_RUNE_11694(ItemID.CHAOS_RUNE_11694, new RuneType[]{RuneType.CHAOS}),
	CHAOS_RUNE_NZ(ItemID.CHAOS_RUNE_NZ, new RuneType[]{RuneType.CHAOS}),

	NATURE_RUNE(ItemID.NATURE_RUNE, new RuneType[]{RuneType.NATURE}),
	NATURE_RUNE_11693(ItemID.NATURE_RUNE_11693, new RuneType[]{RuneType.NATURE}),

	LAW_RUNE(ItemID.LAW_RUNE, new RuneType[]{RuneType.LAW}),
	LAW_RUNE_6434(ItemID.LAW_RUNE_6434, new RuneType[]{RuneType.LAW}),
	LAW_RUNE_11695(ItemID.LAW_RUNE_11695, new RuneType[]{RuneType.LAW}),

	DEATH_RUNE(ItemID.DEATH_RUNE, new RuneType[]{RuneType.DEATH}),
	DEATH_RUNE_6432(ItemID.DEATH_RUNE_6432, new RuneType[]{RuneType.DEATH}),
	DEATH_RUNE_11692(ItemID.DEATH_RUNE_11692, new RuneType[]{RuneType.DEATH}),
	DEATH_RUNE_NZ(ItemID.DEATH_RUNE_NZ, new RuneType[]{RuneType.DEATH}),

	ASTRAL_RUNE(ItemID.ASTRAL_RUNE, new RuneType[]{RuneType.ASTRAL}),
	ASTRAL_RUNE_11699(ItemID.ASTRAL_RUNE_11699, new RuneType[]{RuneType.ASTRAL}),

	BLOOD_RUNE(ItemID.BLOOD_RUNE, new RuneType[]{RuneType.BLOOD}),
	BLOOD_RUNE_11697(ItemID.BLOOD_RUNE_11697, new RuneType[]{RuneType.BLOOD}),
	BLOOD_RUNE_NZ(ItemID.BLOOD_RUNE_NZ, new RuneType[]{RuneType.BLOOD}),

	SOUL_RUNE(ItemID.SOUL_RUNE, new RuneType[]{RuneType.SOUL}),
	SOUL_RUNE_11698(ItemID.SOUL_RUNE_11698, new RuneType[]{RuneType.SOUL}),

	WRATH_RUNE(ItemID.WRATH_RUNE, new RuneType[]{RuneType.WRATH}),
	WRATH_RUNE_22208(ItemID.WRATH_RUNE_22208, new RuneType[]{RuneType.WRATH}),

	// Combo runes
	MIST_RUNE(ItemID.MIST_RUNE, new RuneType[]{RuneType.AIR, RuneType.WATER}),
	DUST_RUNE(ItemID.DUST_RUNE, new RuneType[]{RuneType.AIR, RuneType.EARTH}),
	MUD_RUNE(ItemID.MUD_RUNE, new RuneType[]{RuneType.WATER, RuneType.EARTH}),
	SMOKE_RUNE(ItemID.SMOKE_RUNE, new RuneType[]{RuneType.AIR, RuneType.FIRE}),
	STEAM_RUNE(ItemID.STEAM_RUNE, new RuneType[]{RuneType.WATER, RuneType.FIRE}),
	LAVA_RUNE(ItemID.LAVA_RUNE, new RuneType[]{RuneType.EARTH, RuneType.FIRE});

	@Getter
	private final int id;

	@Getter
	private final RuneType[] matchingTypes;

	private static final Map<Integer, RuneItem> RUNE_ITEM_MAP;

	static
	{
		ImmutableMap.Builder<Integer, RuneItem> builder = new ImmutableMap.Builder<>();

		for (RuneItem item : values())
		{
			builder.put(item.getId(), item);
		}
		RUNE_ITEM_MAP = builder.build();
	}

	public static RuneItem getRuneItem(int itemId)
	{
		return RUNE_ITEM_MAP.getOrDefault(itemId, null);
	}
}
