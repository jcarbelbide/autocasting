package com.autocasting.datatypes;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

/**
 * Base rune types, these are meant to be the costs for certain spells. Physical runes are similar but different,
 * as many different item ids can exist for the same rune (e.g. NMZ runes),
 * and a single id can be two runes at once (combo runes)
 */
public enum RuneType
{
	AIR,
	WATER,
	EARTH,
	FIRE,
	MIND,
	BODY,
	COSMIC,
	CHAOS,
	NATURE,
	LAW,
	DEATH,
	ASTRAL,
	BLOOD,
	SOUL,
	WRATH
}
