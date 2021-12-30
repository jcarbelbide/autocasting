package net.runelite.client.plugins.skilllevelnotifications.src.main.java.com.skilllevelnotifications;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.runelite.api.VarClientInt;

//TODO: Add Magic Dart, Crumble Undead, Flames of Zamorak, Saradomin Strike, Claws of Guthix

@RequiredArgsConstructor
@Getter
enum AutocastSpells
{
    NOSPELL("No spell selected", VarClientInt.AUTOCAST_SPELL, -1, 0),

    WIND_STRIKE("Wind Strike", VarClientInt.AUTOCAST_SPELL, 1 , 1),
    WATER_STRIKE("Water Strike", VarClientInt.AUTOCAST_SPELL, 5 , 2),
    EARTH_STRIKE("Earth Strike", VarClientInt.AUTOCAST_SPELL, 9 , 3),
    FIRE_STRIKE("Fire Strike", VarClientInt.AUTOCAST_SPELL, 13, 4),

    WIND_BOLT("Wind Bolt", VarClientInt.AUTOCAST_SPELL, 17, 5),
    WATER_BOLT("Water Bolt", VarClientInt.AUTOCAST_SPELL, 23, 6),
    EARTH_BOLT("Earth Bolt", VarClientInt.AUTOCAST_SPELL, 29, 7),
    FIRE_BOLT("Fire Bolt", VarClientInt.AUTOCAST_SPELL, 35, 8),

    WIND_BLAST("Wind Blast", VarClientInt.AUTOCAST_SPELL, 41,  9),
    WATER_BLAST("Water Blast", VarClientInt.AUTOCAST_SPELL, 47, 10),
    EARTH_BLAST("Earth Blast", VarClientInt.AUTOCAST_SPELL, 53, 11),
    FIRE_BLAST("Fire Blast", VarClientInt.AUTOCAST_SPELL, 59, 12),

    WIND_WAVE("Wind Wave", VarClientInt.AUTOCAST_SPELL, 62, 13),
    WATER_WAVE("Water Wave", VarClientInt.AUTOCAST_SPELL, 65, 14),
    EARTH_WAVE("Earth Wave", VarClientInt.AUTOCAST_SPELL, 70, 15),
    FIRE_WAVE("Fire Wave", VarClientInt.AUTOCAST_SPELL, 75, 16),

    IBANS_BLAST("Iban's Blast", VarClientInt.AUTOCAST_SPELL, 50, 47),

    WIND_SURGE("Wind Surge", VarClientInt.AUTOCAST_SPELL, 81, 48),
    WATER_SURGE("Water Surge", VarClientInt.AUTOCAST_SPELL, 85, 49),
    EARTH_SURGE("Earth Surge", VarClientInt.AUTOCAST_SPELL, 90, 50),
    FIRE_SURGE("Fire Surge", VarClientInt.AUTOCAST_SPELL, 95, 51);


    private final String name;
    private final VarClientInt autocastSpellVarClientInt;
    private final int levelRequirement;
    private final int autocastSpellID;
}