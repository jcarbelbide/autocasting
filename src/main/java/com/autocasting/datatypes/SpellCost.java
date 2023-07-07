package com.autocasting.datatypes;

import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
@Getter
public enum SpellCost
{
	WIND_STRIKE(1, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.MIND, 1),
		new RuneTypeQuantity(RuneType.AIR, 1)
	}),

	WATER_STRIKE(2, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.MIND, 1),
		new RuneTypeQuantity(RuneType.WATER, 1),
		new RuneTypeQuantity(RuneType.AIR, 1),
	}),

	EARTH_STRIKE(3, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.MIND, 1),
		new RuneTypeQuantity(RuneType.EARTH, 2),
		new RuneTypeQuantity(RuneType.AIR, 1),
	}),

	FIRE_STRIKE(4, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.MIND, 1),
		new RuneTypeQuantity(RuneType.FIRE, 3),
		new RuneTypeQuantity(RuneType.AIR, 2),
	}),

	WIND_BOLT(5, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.CHAOS, 1),
		new RuneTypeQuantity(RuneType.AIR, 2)
	}),

	WATER_BOLT(6, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.CHAOS, 1),
		new RuneTypeQuantity(RuneType.WATER, 2),
		new RuneTypeQuantity(RuneType.AIR, 2)
	}),

	EARTH_BOLT(7, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.CHAOS, 1),
		new RuneTypeQuantity(RuneType.EARTH, 3),
		new RuneTypeQuantity(RuneType.AIR, 2)
	}),

	FIRE_BOLT(8, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.CHAOS, 1),
		new RuneTypeQuantity(RuneType.FIRE, 4),
		new RuneTypeQuantity(RuneType.AIR, 3)
	}),

	WIND_BLAST(9, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 1),
		new RuneTypeQuantity(RuneType.AIR, 3)
	}),

	WATER_BLAST(10, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 1),
		new RuneTypeQuantity(RuneType.WATER, 3),
		new RuneTypeQuantity(RuneType.AIR, 3)
	}),

	EARTH_BLAST(11, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 1),
		new RuneTypeQuantity(RuneType.EARTH, 4),
		new RuneTypeQuantity(RuneType.AIR, 3)
	}),

	FIRE_BLAST(12, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 1),
		new RuneTypeQuantity(RuneType.FIRE, 5),
		new RuneTypeQuantity(RuneType.AIR, 4)
	}),

	WIND_WAVE(13, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.BLOOD, 1),
		new RuneTypeQuantity(RuneType.AIR, 5)
	}),

	WATER_WAVE(14, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.BLOOD, 1),
		new RuneTypeQuantity(RuneType.WATER, 7),
		new RuneTypeQuantity(RuneType.AIR, 5)
	}),

	EARTH_WAVE(15, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.BLOOD, 1),
		new RuneTypeQuantity(RuneType.EARTH, 7),
		new RuneTypeQuantity(RuneType.AIR, 5)
	}),

	FIRE_WAVE(16, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.BLOOD, 1),
		new RuneTypeQuantity(RuneType.FIRE, 7),
		new RuneTypeQuantity(RuneType.AIR, 5)
	}),

	CRUMBLE_UNDEAD(17, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.CHAOS, 1),
		new RuneTypeQuantity(RuneType.EARTH, 2),
		new RuneTypeQuantity(RuneType.AIR, 2)
	}),

	MAGIC_DART(18, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 1),
		new RuneTypeQuantity(RuneType.MIND, 4),
	}),

	CLAWS_OF_GUTHIX(19, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.BLOOD, 2),
		new RuneTypeQuantity(RuneType.AIR, 4),
		new RuneTypeQuantity(RuneType.FIRE, 1)
	}),

	FLAMES_OF_ZAMORAK(20, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.BLOOD, 2),
		new RuneTypeQuantity(RuneType.AIR, 1),
		new RuneTypeQuantity(RuneType.FIRE, 4)
	}),

	SMOKE_RUSH(31, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 2),
		new RuneTypeQuantity(RuneType.CHAOS, 2),
		new RuneTypeQuantity(RuneType.AIR, 1),
		new RuneTypeQuantity(RuneType.FIRE, 1)
	}),

	SHADOW_RUSH(32, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 2),
		new RuneTypeQuantity(RuneType.CHAOS, 2),
		new RuneTypeQuantity(RuneType.SOUL, 1),
		new RuneTypeQuantity(RuneType.AIR, 1)
	}),

	BLOOD_RUSH(33, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 2),
		new RuneTypeQuantity(RuneType.CHAOS, 2),
		new RuneTypeQuantity(RuneType.BLOOD, 1),
	}),

	ICE_RUSH(34, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 2),
		new RuneTypeQuantity(RuneType.CHAOS, 2),
		new RuneTypeQuantity(RuneType.WATER, 2),
	}),

	SMOKE_BURST(35, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 2),
		new RuneTypeQuantity(RuneType.CHAOS, 4),
		new RuneTypeQuantity(RuneType.AIR, 2),
		new RuneTypeQuantity(RuneType.FIRE, 2)
	}),

	SHADOW_BURST(36, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 2),
		new RuneTypeQuantity(RuneType.CHAOS, 4),
		new RuneTypeQuantity(RuneType.SOUL, 2),
		new RuneTypeQuantity(RuneType.AIR, 1)
	}),

	BLOOD_BURST(37, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 2),
		new RuneTypeQuantity(RuneType.CHAOS, 4),
		new RuneTypeQuantity(RuneType.BLOOD, 2),
	}),

	ICE_BURST(38, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 2),
		new RuneTypeQuantity(RuneType.CHAOS, 4),
		new RuneTypeQuantity(RuneType.WATER, 4),
	}),

	SMOKE_BLITZ(39, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 2),
		new RuneTypeQuantity(RuneType.BLOOD, 2),
		new RuneTypeQuantity(RuneType.AIR, 2),
		new RuneTypeQuantity(RuneType.FIRE, 2)
	}),

	SHADOW_BLITZ(40, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 2),
		new RuneTypeQuantity(RuneType.BLOOD, 2),
		new RuneTypeQuantity(RuneType.SOUL, 2),
		new RuneTypeQuantity(RuneType.AIR, 2)
	}),

	BLOOD_BLITZ(41, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 2),
		new RuneTypeQuantity(RuneType.BLOOD, 4),
	}),

	ICE_BLITZ(42, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 2),
		new RuneTypeQuantity(RuneType.BLOOD, 2),
		new RuneTypeQuantity(RuneType.WATER, 3),
	}),

	SMOKE_BARRAGE(43, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 4),
		new RuneTypeQuantity(RuneType.BLOOD, 2),
		new RuneTypeQuantity(RuneType.AIR, 4),
		new RuneTypeQuantity(RuneType.FIRE, 4)
	}),

	SHADOW_BARRAGE(44, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 4),
		new RuneTypeQuantity(RuneType.BLOOD, 2),
		new RuneTypeQuantity(RuneType.SOUL, 3),
		new RuneTypeQuantity(RuneType.AIR, 4)
	}),

	BLOOD_BARRAGE(45, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 4),
		new RuneTypeQuantity(RuneType.BLOOD, 4),
		new RuneTypeQuantity(RuneType.SOUL, 1),
	}),

	ICE_BARRAGE(46, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 4),
		new RuneTypeQuantity(RuneType.BLOOD, 2),
		new RuneTypeQuantity(RuneType.WATER, 6),
	}),

	IBAN_BLAST(47, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 1),
		new RuneTypeQuantity(RuneType.FIRE, 5)
	}),

	WIND_SURGE(48, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.WRATH, 1),
		new RuneTypeQuantity(RuneType.AIR, 7)
	}),

	WATER_SURGE(49, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.WRATH, 1),
		new RuneTypeQuantity(RuneType.WATER, 10),
		new RuneTypeQuantity(RuneType.AIR, 7)
	}),

	EARTH_SURGE(50, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.WRATH, 1),
		new RuneTypeQuantity(RuneType.EARTH, 10),
		new RuneTypeQuantity(RuneType.AIR, 7)
	}),

	FIRE_SURGE(51, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.WRATH, 1),
		new RuneTypeQuantity(RuneType.FIRE, 10),
		new RuneTypeQuantity(RuneType.AIR, 7)
	}),

	SARADOMIN_STRIKE(52, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.BLOOD, 2),
		new RuneTypeQuantity(RuneType.AIR, 4),
		new RuneTypeQuantity(RuneType.FIRE, 2)
	}),

	INFERIOR_DEMONBANE(53, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.CHAOS, 1),
		new RuneTypeQuantity(RuneType.FIRE, 4)
	}),

	SUPERIOR_DEMONBANE(54, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.SOUL, 1),
		new RuneTypeQuantity(RuneType.FIRE, 8)
	}),

	DARK_DEMONBANE(55, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.SOUL, 2),
		new RuneTypeQuantity(RuneType.FIRE, 12)
	}),

	GHOSTLY_GRASP(56, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.CHAOS, 1),
		new RuneTypeQuantity(RuneType.AIR, 4)
	}),

	SKELETAL_GRASP(57, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 1),
		new RuneTypeQuantity(RuneType.EARTH, 8)
	}),

	UNDEAD_GRASP(58, new RuneTypeQuantity[]{
		new RuneTypeQuantity(RuneType.DEATH, 1),
		new RuneTypeQuantity(RuneType.FIRE, 12)
	});

	@Getter
	private final int varbitValue;

	@Getter
	private final RuneTypeQuantity[] runeCost;
}
