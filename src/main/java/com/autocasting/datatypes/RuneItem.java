package com.autocasting.datatypes;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import net.runelite.api.gameval.ItemID;

import java.util.Map;

/**
 * Represents a physical rune, like you could have in your inventory.
 * May correspond to one or more rune types (i.e. combination runes)
 */
@RequiredArgsConstructor
@Getter
public enum RuneItem
{
	AIR_RUNE(ItemID.AIRRUNE, new RuneType[]{RuneType.AIR}),
	ROGUETRADER_AIRRUNE(ItemID.ROGUETRADER_AIRRUNE, new RuneType[]{RuneType.AIR}),
	_100GUIDE_AIRRUNE_DUM(ItemID._100GUIDE_AIRRUNE_DUM, new RuneType[]{RuneType.AIR}),
	SLUG2_RUNE_AIR(ItemID.SLUG2_RUNE_AIR, new RuneType[]{RuneType.AIR}),
	FAKE_AIRRUNE(ItemID.FAKE_AIRRUNE, new RuneType[]{RuneType.AIR}),
	NZONE_AIRRUNE(ItemID.NZONE_AIRRUNE, new RuneType[]{RuneType.AIR}),

	WATER_RUNE(ItemID.WATERRUNE, new RuneType[]{RuneType.WATER}),
	ROGUETRADER_WATERRUNE(ItemID.ROGUETRADER_WATERRUNE, new RuneType[]{RuneType.WATER}),
	_100GUIDE_WATERRUNE_DUM(ItemID._100GUIDE_WATERRUNE_DUM, new RuneType[]{RuneType.WATER}),
	SLUG2_RUNE_WATER(ItemID.SLUG2_RUNE_WATER, new RuneType[]{RuneType.WATER}),
	FAKE_WATERRUNE(ItemID.FAKE_WATERRUNE, new RuneType[]{RuneType.WATER}),
	NZONE_WATERRUNE(ItemID.NZONE_WATERRUNE, new RuneType[]{RuneType.WATER}),

	EARTH_RUNE(ItemID.EARTHRUNE, new RuneType[]{RuneType.EARTH}),
	ROGUETRADER_EARTHRUNE(ItemID.ROGUETRADER_EARTHRUNE, new RuneType[]{RuneType.EARTH}),
	SLUG2_RUNE_EARTH(ItemID.SLUG2_RUNE_EARTH, new RuneType[]{RuneType.EARTH}),
	FAKE_EARTHRUNE(ItemID.FAKE_EARTHRUNE, new RuneType[]{RuneType.EARTH}),
	NZONE_EARTHRUNE(ItemID.NZONE_EARTHRUNE, new RuneType[]{RuneType.EARTH}),

	FIRE_RUNE(ItemID.FIRERUNE, new RuneType[]{RuneType.FIRE}),
	ROGUETRADER_FIRERUNE(ItemID.ROGUETRADER_FIRERUNE, new RuneType[]{RuneType.FIRE}),
	_100GUIDE_FIRERUNE_DUM(ItemID._100GUIDE_FIRERUNE_DUM, new RuneType[]{RuneType.FIRE}),
	SLUG2_RUNE_FIRE(ItemID.SLUG2_RUNE_FIRE, new RuneType[]{RuneType.FIRE}),
	FAKE_FIRERUNE(ItemID.FAKE_FIRERUNE, new RuneType[]{RuneType.FIRE}),
	NZONE_FIRERUNE(ItemID.NZONE_FIRERUNE, new RuneType[]{RuneType.FIRE}),
	SUNFIRE_RUNE(ItemID.SUNFIRERUNE, new RuneType[]{RuneType.FIRE}),

	MIND_RUNE(ItemID.MINDRUNE, new RuneType[]{RuneType.MIND}),
	ROGUETRADER_MINDRUNE(ItemID.ROGUETRADER_MINDRUNE, new RuneType[]{RuneType.MIND}),
	SLUG2_RUNE_MIND(ItemID.SLUG2_RUNE_MIND, new RuneType[]{RuneType.MIND}),
	FAKE_MINDRUNE(ItemID.FAKE_MINDRUNE, new RuneType[]{RuneType.MIND}),

	BODY_RUNE(ItemID.BODYRUNE, new RuneType[]{RuneType.BODY}),
	ROGUETRADER_BODYRUNE(ItemID.ROGUETRADER_BODYRUNE, new RuneType[]{RuneType.BODY}),
	FAKE_BODYRUNE(ItemID.FAKE_BODYRUNE, new RuneType[]{RuneType.BODY}),

	COSMIC_RUNE(ItemID.COSMICRUNE, new RuneType[]{RuneType.COSMIC}),
	FAKE_COSMICRUNE(ItemID.FAKE_COSMICRUNE, new RuneType[]{RuneType.COSMIC}),

	CHAOS_RUNE(ItemID.CHAOSRUNE, new RuneType[]{RuneType.CHAOS}),
	ROGUETRADER_CHAOSRUNE(ItemID.ROGUETRADER_CHAOSRUNE, new RuneType[]{RuneType.CHAOS}),
	_100GUIDE_CHAOSRUNE_DUM(ItemID._100GUIDE_CHAOSRUNE_DUM, new RuneType[]{RuneType.CHAOS}),
	FAKE_CHAOSRUNE(ItemID.FAKE_CHAOSRUNE, new RuneType[]{RuneType.CHAOS}),
	NZONE_CHAOSRUNE(ItemID.NZONE_CHAOSRUNE, new RuneType[]{RuneType.CHAOS}),

	NATURE_RUNE(ItemID.NATURERUNE, new RuneType[]{RuneType.NATURE}),
	FAKE_NATURERUNE(ItemID.FAKE_NATURERUNE, new RuneType[]{RuneType.NATURE}),

	LAW_RUNE(ItemID.LAWRUNE, new RuneType[]{RuneType.LAW}),
	ROGUETRADER_LAWRUNE(ItemID.ROGUETRADER_LAWRUNE, new RuneType[]{RuneType.LAW}),
	FAKE_LAWRUNE(ItemID.FAKE_LAWRUNE, new RuneType[]{RuneType.LAW}),

	DEATH_RUNE(ItemID.DEATHRUNE, new RuneType[]{RuneType.DEATH}),
	ROGUETRADER_DEATHRUNE(ItemID.ROGUETRADER_DEATHRUNE, new RuneType[]{RuneType.DEATH}),
	FAKE_DEATHRUNE(ItemID.FAKE_DEATHRUNE, new RuneType[]{RuneType.DEATH}),
	NZONE_DEATHRUNE(ItemID.NZONE_DEATHRUNE, new RuneType[]{RuneType.DEATH}),

	ASTRAL_RUNE(ItemID.ASTRALRUNE, new RuneType[]{RuneType.ASTRAL}),
	FAKE_ASTRALRUNE(ItemID.FAKE_ASTRALRUNE, new RuneType[]{RuneType.ASTRAL}),

	BLOOD_RUNE(ItemID.BLOODRUNE, new RuneType[]{RuneType.BLOOD}),
	FAKE_BLOODRUNE(ItemID.FAKE_BLOODRUNE, new RuneType[]{RuneType.BLOOD}),
	NZONE_BLOODRUNE(ItemID.NZONE_BLOODRUNE, new RuneType[]{RuneType.BLOOD}),

	SOUL_RUNE(ItemID.SOULRUNE, new RuneType[]{RuneType.SOUL}),
	FAKE_SOULRUNE(ItemID.FAKE_SOULRUNE, new RuneType[]{RuneType.SOUL}),

	WRATH_RUNE(ItemID.WRATHRUNE, new RuneType[]{RuneType.WRATH}),
	FAKE_WRATHRUNE(ItemID.FAKE_WRATHRUNE, new RuneType[]{RuneType.WRATH}),

	// Combo runes
	MIST_RUNE(ItemID.MISTRUNE, new RuneType[]{RuneType.AIR, RuneType.WATER}),
	DUST_RUNE(ItemID.DUSTRUNE, new RuneType[]{RuneType.AIR, RuneType.EARTH}),
	MUD_RUNE(ItemID.MUDRUNE, new RuneType[]{RuneType.WATER, RuneType.EARTH}),
	SMOKE_RUNE(ItemID.SMOKERUNE, new RuneType[]{RuneType.AIR, RuneType.FIRE}),
	STEAM_RUNE(ItemID.STEAMRUNE, new RuneType[]{RuneType.WATER, RuneType.FIRE}),
	LAVA_RUNE(ItemID.LAVARUNE, new RuneType[]{RuneType.EARTH, RuneType.FIRE}),
	AETHER_RUNE(ItemID.AETHERRUNE, new RuneType[]{RuneType.SOUL, RuneType.COSMIC});

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
