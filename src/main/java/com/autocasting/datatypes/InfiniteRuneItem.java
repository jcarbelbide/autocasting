package com.autocasting.datatypes;


import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.api.EquipmentInventorySlot;
import net.runelite.api.Item;
import net.runelite.api.ItemID;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public enum InfiniteRuneItem
{
	STAFF_OF_AIR(ItemID.STAFF_OF_AIR, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.AIR}),
	AIR_BATTLESTAFF(ItemID.AIR_BATTLESTAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.AIR}),
	MYSTIC_AIR_STAFF(ItemID.MYSTIC_AIR_STAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.AIR}),

	STAFF_OF_WATER(ItemID.STAFF_OF_WATER, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.WATER}),
	WATER_BATTLESTAFF(ItemID.WATER_BATTLESTAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.WATER}),
	MYSTIC_WATER_STAFF(ItemID.MYSTIC_WATER_STAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.WATER}),

	STAFF_OF_EARTH(ItemID.STAFF_OF_EARTH, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.EARTH}),
	EARTH_BATTLESTAFF(ItemID.EARTH_BATTLESTAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.EARTH}),
	MYSTIC_EARTH_STAFF(ItemID.MYSTIC_EARTH_STAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.EARTH}),

	STAFF_OF_FIRE(ItemID.STAFF_OF_FIRE, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.FIRE}),
	FIRE_BATTLESTAFF(ItemID.FIRE_BATTLESTAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.FIRE}),
	MYSTIC_FIRE_STAFF(ItemID.MYSTIC_FIRE_STAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.FIRE}),

	MIST_BATTLESTAFF(ItemID.MIST_BATTLESTAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.AIR, RuneType.WATER}),
	DUST_BATTLESTAFF(ItemID.DUST_BATTLESTAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.AIR, RuneType.EARTH}),
	MUD_BATTLESTAFF(ItemID.MUD_BATTLESTAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.WATER, RuneType.EARTH}),
	SMOKE_BATTLESTAFF(ItemID.SMOKE_BATTLESTAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.AIR, RuneType.FIRE}),
	STEAM_BATTLESTAFF(ItemID.STEAM_BATTLESTAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.WATER, RuneType.FIRE}),
	LAVA_BATTLESTAFF(ItemID.LAVA_BATTLESTAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.EARTH, RuneType.FIRE}),

	MYSTIC_MIST_STAFF(ItemID.MYSTIC_MIST_STAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.AIR, RuneType.WATER}),
	MYSTIC_DUST_STAFF(ItemID.MYSTIC_DUST_STAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.AIR, RuneType.EARTH}),
	MYSTIC_MUD_STAFF(ItemID.MYSTIC_MUD_STAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.WATER, RuneType.EARTH}),
	MYSTIC_SMOKE_STAFF(ItemID.MYSTIC_SMOKE_STAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.AIR, RuneType.FIRE}),
	MYSTIC_STEAM_STAFF(ItemID.MYSTIC_STEAM_STAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.WATER, RuneType.FIRE}),
	MYSTIC_LAVA_STAFF(ItemID.MYSTIC_LAVA_STAFF, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.EARTH, RuneType.FIRE}),

	KODAI_WAND(ItemID.KODAI_WAND, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.WATER}),
	KODAI_WAND_23626(ItemID.KODAI_WAND_23626, EquipmentInventorySlot.WEAPON, new RuneType[]{RuneType.WATER}),

	TOME_OF_WATER(ItemID.TOME_OF_WATER, EquipmentInventorySlot.SHIELD, new RuneType[]{RuneType.WATER}),
	TOME_OF_FIRE(ItemID.TOME_OF_FIRE, EquipmentInventorySlot.SHIELD, new RuneType[]{RuneType.FIRE});


	@Getter
	private final int itemId;

	@Getter
	private final EquipmentInventorySlot slot;

	@Getter
	private final RuneType[] matchingTypes;

	private static final Map<Integer, InfiniteRuneItem> INFINITE_RUNE_ITEM_MAP;

	static
	{
		ImmutableMap.Builder<Integer, InfiniteRuneItem> builder = new ImmutableMap.Builder<>();

		for (InfiniteRuneItem item : values())
		{
			builder.put(item.getItemId(), item);
		}

		INFINITE_RUNE_ITEM_MAP = builder.build();
	}

	public static InfiniteRuneItem getInfiniteRuneItem(int itemId)
	{
		return INFINITE_RUNE_ITEM_MAP.getOrDefault(itemId, null);
	}
}
