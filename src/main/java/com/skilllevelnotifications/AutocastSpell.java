package net.runelite.client.plugins.skilllevelnotifications.src.main.java.com.skilllevelnotifications;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.api.Varbits;

import java.util.Map;

//TODO: Add Magic Dart, Crumble Undead, Flames of Zamorak, Saradomin Strike, Claws of Guthix

@RequiredArgsConstructor
@Getter
enum AutocastSpell
{
    NOSPELL("No spell selected", Varbits.AUTOCAST_SPELL, -1, 0),

    WIND_STRIKE("Wind Strike", Varbits.AUTOCAST_SPELL, 1 , 1),
    WATER_STRIKE("Water Strike", Varbits.AUTOCAST_SPELL, 5 , 2),
    EARTH_STRIKE("Earth Strike", Varbits.AUTOCAST_SPELL, 9 , 3),
    FIRE_STRIKE("Fire Strike", Varbits.AUTOCAST_SPELL, 13, 4),

    WIND_BOLT("Wind Bolt", Varbits.AUTOCAST_SPELL, 17, 5),
    WATER_BOLT("Water Bolt", Varbits.AUTOCAST_SPELL, 23, 6),
    EARTH_BOLT("Earth Bolt", Varbits.AUTOCAST_SPELL, 29, 7),
    FIRE_BOLT("Fire Bolt", Varbits.AUTOCAST_SPELL, 35, 8),

    WIND_BLAST("Wind Blast", Varbits.AUTOCAST_SPELL, 41,  9),
    WATER_BLAST("Water Blast", Varbits.AUTOCAST_SPELL, 47, 10),
    EARTH_BLAST("Earth Blast", Varbits.AUTOCAST_SPELL, 53, 11),
    FIRE_BLAST("Fire Blast", Varbits.AUTOCAST_SPELL, 59, 12),

    WIND_WAVE("Wind Wave", Varbits.AUTOCAST_SPELL, 62, 13),
    WATER_WAVE("Water Wave", Varbits.AUTOCAST_SPELL, 65, 14),
    EARTH_WAVE("Earth Wave", Varbits.AUTOCAST_SPELL, 70, 15),
    FIRE_WAVE("Fire Wave", Varbits.AUTOCAST_SPELL, 75, 16),

    IBANS_BLAST("Iban's Blast", Varbits.AUTOCAST_SPELL, 50, 47),

    WIND_SURGE("Wind Surge", Varbits.AUTOCAST_SPELL, 81, 48),
    WATER_SURGE("Water Surge", Varbits.AUTOCAST_SPELL, 85, 49),
    EARTH_SURGE("Earth Surge", Varbits.AUTOCAST_SPELL, 90, 50),
    FIRE_SURGE("Fire Surge", Varbits.AUTOCAST_SPELL, 95, 51);

    @Getter
    private final String name;

    @Getter
    private final Varbits autocastSpellVarClientInt;

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