package net.runelite.client.plugins.autocastutilities.src.main.java.com.autocastutilities;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.api.SpriteID;

import java.util.Map;

@RequiredArgsConstructor
@Getter
enum AutocastSpell
{
    NO_SPELL("No spell selected", SpriteID.ROUND_CHECK_BOX_CROSSED , -1, 0),

    WIND_STRIKE("Wind Strike", SpriteID.SPELL_WIND_STRIKE, 1 , 1),
    WATER_STRIKE("Water Strike", SpriteID.SPELL_WATER_STRIKE, 5 , 2),
    EARTH_STRIKE("Earth Strike", SpriteID.SPELL_EARTH_STRIKE, 9 , 3),
    FIRE_STRIKE("Fire Strike", SpriteID.SPELL_FIRE_STRIKE, 13, 4),

    WIND_BOLT("Wind Bolt", SpriteID.SPELL_WIND_BOLT, 17, 5),
    WATER_BOLT("Water Bolt", SpriteID.SPELL_WATER_BOLT, 23, 6),
    EARTH_BOLT("Earth Bolt", SpriteID.SPELL_EARTH_BOLT, 29, 7),
    FIRE_BOLT("Fire Bolt", SpriteID.SPELL_FIRE_BOLT, 35, 8),

    WIND_BLAST("Wind Blast", SpriteID.SPELL_WIND_BLAST, 41,  9),
    WATER_BLAST("Water Blast", SpriteID.SPELL_WATER_BLAST, 47, 10),
    EARTH_BLAST("Earth Blast", SpriteID.SPELL_EARTH_BLAST, 53, 11),
    FIRE_BLAST("Fire Blast", SpriteID.SPELL_FIRE_BLAST, 59, 12),

    WIND_WAVE("Wind Wave", SpriteID.SPELL_WIND_WAVE, 62, 13),
    WATER_WAVE("Water Wave", SpriteID.SPELL_WATER_WAVE, 65, 14),
    EARTH_WAVE("Earth Wave", SpriteID.SPELL_EARTH_WAVE, 70, 15),
    FIRE_WAVE("Fire Wave", SpriteID.SPELL_FIRE_WAVE, 75, 16),

    WIND_SURGE("Wind Surge", SpriteID.SPELL_WIND_SURGE, 81, 48),
    WATER_SURGE("Water Surge", SpriteID.SPELL_WATER_SURGE, 85, 49),
    EARTH_SURGE("Earth Surge", SpriteID.SPELL_EARTH_SURGE, 90, 50),
    FIRE_SURGE("Fire Surge", SpriteID.SPELL_FIRE_SURGE, 95, 51),


    SMOKE_RUSH("Smoke Rush", SpriteID.SPELL_SMOKE_RUSH, 50, 31),
    SHADOW_RUSH("Shadow Rush", SpriteID.SPELL_SHADOW_RUSH, 52, 32),
    BLOOD_RUSH("Blood Rush", SpriteID.SPELL_BLOOD_RUSH, 56, 33),
    ICE_RUSH("Ice Rush", SpriteID.SPELL_ICE_RUSH, 58, 34),

    SMOKE_BURST("Smoke Burst", SpriteID.SPELL_SMOKE_BURST, 62, 35),
    SHADOW_BURST("Shadow Burst", SpriteID.SPELL_SHADOW_BURST, 64, 36),
    BLOOD_BURST("Blood Burst", SpriteID.SPELL_BLOOD_BURST, 68, 37),
    ICE_BURST("Ice Burst", SpriteID.SPELL_ICE_BURST, 70, 38),

    SMOKE_BLITZ("Smoke Blitz", SpriteID.SPELL_SMOKE_BLITZ, 74, 39),
    SHADOW_BLITZ("Shadow Blitz", SpriteID.SPELL_SHADOW_BLITZ, 76, 40),
    BLOOD_BLITZ("Blood Blitz", SpriteID.SPELL_BLOOD_BLITZ, 80, 41),
    ICE_BLITZ("Ice Blitz", SpriteID.SPELL_ICE_BLITZ, 82, 42),

    SMOKE_BARRAGE("Smoke Barrage", SpriteID.SPELL_SMOKE_BARRAGE, 86, 43),
    SHADOW_BARRAGE("Shadow Barrage", SpriteID.SPELL_SHADOW_BARRAGE, 88, 44),
    BLOOD_BARRAGE("Blood Barrage", SpriteID.SPELL_BLOOD_BARRAGE, 92, 45),
    ICE_BARRAGE("Ice Barrage", SpriteID.SPELL_ICE_BARRAGE, 94, 46),


    INFERIOR_DEMONBANE("Inferior Demonbane", SpriteID.SPELL_INFERIOR_DEMONBANE, 44, 53),
    SUPERIOR_DEMONBANE("Superior Demonbane", SpriteID.SPELL_SUPERIOR_DEMONBANE, 62, 54),
    DARK_DEMONBANE("Dark Demonbane", SpriteID.SPELL_DARK_DEMONBANE, 82, 55),

    GHOSTLY_GRASP("Ghostly Grasp", SpriteID.SPELL_GHOSTLY_GRASP, 35, 56),
    SKELETAL_GRASP("Skeletal Grasp", SpriteID.SPELL_SKELETAL_GRASP, 56, 57),
    UNDEAD_GRASP("Undead Grasp", SpriteID.SPELL_UNDEAD_GRASP, 79, 58),


    CLAWS_OF_GUTHIX("Claws of Guthix", SpriteID.SPELL_CLAWS_OF_GUTHIC, 60, 19),
    FLAMES_OF_ZAMORAK("Flames of Zamorak", SpriteID.SPELL_FLAMES_OF_ZAMORAK, 60, 20),
    SARADOMIN_STRIKE("Saradomin Strike", SpriteID.SPELL_SARADOMIN_STRIKE, 60, 52),


    IBANS_BLAST("Iban's Blast", SpriteID.SPELL_IBAN_BLAST, 50, 47),
    CRUMBLE_UNDEAD("Crumble Undead", SpriteID.SPELL_CRUMBLE_UNDEAD, 39, 17),
    MAGIC_DART("Magic Dart", SpriteID.SPELL_MAGIC_DART, 50, 18);

    @Getter
    private final String name;

    @Getter
    private final int spriteID;

    @Getter
    private final int levelRequirement;

    @Getter
    private final int autocastSpellID;

    private static final Map<Integer, AutocastSpell> AUTOCAST_SPELLS_MAP;

    static
    {
        ImmutableMap.Builder<Integer, AutocastSpell> builder = new ImmutableMap.Builder<>();

        for (AutocastSpell spell : values())
        {
            builder.put(spell.getAutocastSpellID(), spell);
        }

        AUTOCAST_SPELLS_MAP = builder.build();
    }

    public static AutocastSpell getAutocastSpell(int autocastSpellID) { return AUTOCAST_SPELLS_MAP.get(autocastSpellID); }
}

/**
 * The currently selected autocast spell.
 * 0        = no spell selected.
 *
 * 1 -> 4   = wind, water, earth, fire strike
 * 5 -> 8   = wind, water, earth, fire bolt
 * 9 -> 12  = wind, water, earth, fire blast
 * 13 -> 16 = wind, water, earth, fire wave
 * 48 -> 51 = wind, water, earth, fire surge
 *
 * 31 -> 34 = Smoke, Shadow, Blood, Ice Rush
 * 35 -> 38 = Smoke, Shadow, Blood, Ice Burst
 * 39 -> 42 = Smoke, Shadow, Blood, Ice Blitz
 * 43 -> 46 = Smoke, Shadow, Blood, Ice Barrage
 *
 * 53 -> 55 = Inferior, Superior, Dark Demonbane
 * 56 -> 58 = Ghostly, Skeletal, Undead Grasp
 *
 * 47       = Iban's blast
 * 17       = Crumble Undead
 * 18       = Magic Dart
 * 19       = Claws of Guthix
 * 20       = Flames of Zamorak
 * 52       = Saradomin Strike
 */
