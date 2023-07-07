package com.autocasting.datatypes;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.api.SpriteID;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public enum Spell
{
	/**
	 * The currently selected autocast spell.
	 * 0        = no spell selected.
	 * <p>
	 * 1 -> 4   = wind, water, earth, fire strike
	 * 5 -> 8   = wind, water, earth, fire bolt
	 * 9 -> 12  = wind, water, earth, fire blast
	 * 13 -> 16 = wind, water, earth, fire wave
	 * 48 -> 51 = wind, water, earth, fire surge
	 * <p>
	 * 31 -> 34 = Smoke, Shadow, Blood, Ice Rush
	 * 35 -> 38 = Smoke, Shadow, Blood, Ice Burst
	 * 39 -> 42 = Smoke, Shadow, Blood, Ice Blitz
	 * 43 -> 46 = Smoke, Shadow, Blood, Ice Barrage
	 * <p>
	 * 53 -> 55 = Inferior, Superior, Dark Demonbane
	 * 56 -> 58 = Ghostly, Skeletal, Undead Grasp
	 * <p>
	 * 47       = Iban's blast
	 * 17       = Crumble Undead
	 * 18       = Magic Dart
	 * 19       = Claws of Guthix
	 * 20       = Flames of Zamorak
	 * 52       = Saradomin Strike
	 */

	NO_SPELL("No spell selected", SpriteID.ROUND_CHECK_BOX_CROSSED, -1, 0, null),

	WIND_STRIKE("Wind Strike", SpriteID.SPELL_WIND_STRIKE, 1, 1, SpellCost.WIND_STRIKE),
	WATER_STRIKE("Water Strike", SpriteID.SPELL_WATER_STRIKE, 5, 2, SpellCost.WATER_STRIKE),
	EARTH_STRIKE("Earth Strike", SpriteID.SPELL_EARTH_STRIKE, 9, 3, SpellCost.EARTH_STRIKE),
	FIRE_STRIKE("Fire Strike", SpriteID.SPELL_FIRE_STRIKE, 13, 4, SpellCost.FIRE_STRIKE),

	WIND_BOLT("Wind Bolt", SpriteID.SPELL_WIND_BOLT, 17, 5, SpellCost.WIND_BOLT),
	WATER_BOLT("Water Bolt", SpriteID.SPELL_WATER_BOLT, 23, 6, SpellCost.WATER_BOLT),
	EARTH_BOLT("Earth Bolt", SpriteID.SPELL_EARTH_BOLT, 29, 7, SpellCost.EARTH_BOLT),
	FIRE_BOLT("Fire Bolt", SpriteID.SPELL_FIRE_BOLT, 35, 8, SpellCost.FIRE_BOLT),

	WIND_BLAST("Wind Blast", SpriteID.SPELL_WIND_BLAST, 41, 9, SpellCost.WIND_BLAST),
	WATER_BLAST("Water Blast", SpriteID.SPELL_WATER_BLAST, 47, 10, SpellCost.WATER_BLAST),
	EARTH_BLAST("Earth Blast", SpriteID.SPELL_EARTH_BLAST, 53, 11, SpellCost.EARTH_BLAST),
	FIRE_BLAST("Fire Blast", SpriteID.SPELL_FIRE_BLAST, 59, 12, SpellCost.FIRE_BLAST),

	WIND_WAVE("Wind Wave", SpriteID.SPELL_WIND_WAVE, 62, 13, SpellCost.WIND_WAVE),
	WATER_WAVE("Water Wave", SpriteID.SPELL_WATER_WAVE, 65, 14, SpellCost.WATER_WAVE),
	EARTH_WAVE("Earth Wave", SpriteID.SPELL_EARTH_WAVE, 70, 15, SpellCost.EARTH_WAVE),
	FIRE_WAVE("Fire Wave", SpriteID.SPELL_FIRE_WAVE, 75, 16, SpellCost.FIRE_WAVE),

	CRUMBLE_UNDEAD("Crumble Undead", SpriteID.SPELL_CRUMBLE_UNDEAD, 39, 17, SpellCost.CRUMBLE_UNDEAD),
	MAGIC_DART("Magic Dart", SpriteID.SPELL_MAGIC_DART, 50, 18, SpellCost.MAGIC_DART),

	CLAWS_OF_GUTHIX("Claws of Guthix", SpriteID.SPELL_CLAWS_OF_GUTHIX, 60, 19, SpellCost.CLAWS_OF_GUTHIX),
	FLAMES_OF_ZAMORAK("Flames of Zamorak", SpriteID.SPELL_FLAMES_OF_ZAMORAK, 60, 20, SpellCost.FLAMES_OF_ZAMORAK),

	// 21 - 30 Unused

	SMOKE_RUSH("Smoke Rush", SpriteID.SPELL_SMOKE_RUSH, 50, 31, SpellCost.SMOKE_RUSH),
	SHADOW_RUSH("Shadow Rush", SpriteID.SPELL_SHADOW_RUSH, 52, 32, SpellCost.SHADOW_RUSH),
	BLOOD_RUSH("Blood Rush", SpriteID.SPELL_BLOOD_RUSH, 56, 33, SpellCost.BLOOD_RUSH),
	ICE_RUSH("Ice Rush", SpriteID.SPELL_ICE_RUSH, 58, 34, SpellCost.ICE_RUSH),

	SMOKE_BURST("Smoke Burst", SpriteID.SPELL_SMOKE_BURST, 62, 35, SpellCost.SMOKE_BURST),
	SHADOW_BURST("Shadow Burst", SpriteID.SPELL_SHADOW_BURST, 64, 36, SpellCost.SHADOW_BURST),
	BLOOD_BURST("Blood Burst", SpriteID.SPELL_BLOOD_BURST, 68, 37, SpellCost.BLOOD_BURST),
	ICE_BURST("Ice Burst", SpriteID.SPELL_ICE_BURST, 70, 38, SpellCost.ICE_BURST),

	SMOKE_BLITZ("Smoke Blitz", SpriteID.SPELL_SMOKE_BLITZ, 74, 39, SpellCost.SMOKE_BLITZ),
	SHADOW_BLITZ("Shadow Blitz", SpriteID.SPELL_SHADOW_BLITZ, 76, 40, SpellCost.SHADOW_BLITZ),
	BLOOD_BLITZ("Blood Blitz", SpriteID.SPELL_BLOOD_BLITZ, 80, 41, SpellCost.BLOOD_BLITZ),
	ICE_BLITZ("Ice Blitz", SpriteID.SPELL_ICE_BLITZ, 82, 42, SpellCost.ICE_BLITZ),

	SMOKE_BARRAGE("Smoke Barrage", SpriteID.SPELL_SMOKE_BARRAGE, 86, 43, SpellCost.SMOKE_BARRAGE),
	SHADOW_BARRAGE("Shadow Barrage", SpriteID.SPELL_SHADOW_BARRAGE, 88, 44, SpellCost.SHADOW_BARRAGE),
	BLOOD_BARRAGE("Blood Barrage", SpriteID.SPELL_BLOOD_BARRAGE, 92, 45, SpellCost.BLOOD_BARRAGE),
	ICE_BARRAGE("Ice Barrage", SpriteID.SPELL_ICE_BARRAGE, 94, 46, SpellCost.ICE_BARRAGE),

	IBAN_BLAST("Iban's Blast", SpriteID.SPELL_IBAN_BLAST, 50, 47, SpellCost.IBAN_BLAST),

	WIND_SURGE("Wind Surge", SpriteID.SPELL_WIND_SURGE, 81, 48, SpellCost.WIND_SURGE),
	WATER_SURGE("Water Surge", SpriteID.SPELL_WATER_SURGE, 85, 49, SpellCost.WATER_SURGE),
	EARTH_SURGE("Earth Surge", SpriteID.SPELL_EARTH_SURGE, 90, 50, SpellCost.EARTH_SURGE),
	FIRE_SURGE("Fire Surge", SpriteID.SPELL_FIRE_SURGE, 95, 51, SpellCost.FIRE_SURGE),

	SARADOMIN_STRIKE("Saradomin Strike", SpriteID.SPELL_SARADOMIN_STRIKE, 60, 52, SpellCost.SARADOMIN_STRIKE),

	INFERIOR_DEMONBANE("Inferior Demonbane", SpriteID.SPELL_INFERIOR_DEMONBANE, 44, 53, SpellCost.INFERIOR_DEMONBANE),
	SUPERIOR_DEMONBANE("Superior Demonbane", SpriteID.SPELL_SUPERIOR_DEMONBANE, 62, 54, SpellCost.SUPERIOR_DEMONBANE),
	DARK_DEMONBANE("Dark Demonbane", SpriteID.SPELL_DARK_DEMONBANE, 82, 55, SpellCost.DARK_DEMONBANE),

	GHOSTLY_GRASP("Ghostly Grasp", SpriteID.SPELL_GHOSTLY_GRASP, 35, 56, SpellCost.GHOSTLY_GRASP),
	SKELETAL_GRASP("Skeletal Grasp", SpriteID.SPELL_SKELETAL_GRASP, 56, 57, SpellCost.SKELETAL_GRASP),
	UNDEAD_GRASP("Undead Grasp", SpriteID.SPELL_UNDEAD_GRASP, 79, 58, SpellCost.UNDEAD_GRASP);

	@Getter
	private final String name;

	@Getter
	private final int spriteID;

	@Getter
	private final int levelRequirement;

	@Getter
	private final int varbitValue;

	@Getter
	private final SpellCost spellCost;

	private static final Map<Integer, Spell> AUTOCAST_SPELLS_MAP;

	static
	{
		ImmutableMap.Builder<Integer, Spell> builder = new ImmutableMap.Builder<>();

		for (Spell spell : values())
		{
			builder.put(spell.getVarbitValue(), spell);
		}
		AUTOCAST_SPELLS_MAP = builder.build();
	}

	public static Spell getSpell(int varbitValue)
	{
		return AUTOCAST_SPELLS_MAP.getOrDefault(varbitValue, null);
	}
}