package com.autocasting.datatypes;

import lombok.Data;

/**
 * A "tuple" of RuneType and a quantity,
 * in other words the amount of a certain rune to cast a spell
 */
@Data
public class RuneTypeQuantity
{
	private final RuneType runeType;
	private final int quantity;
}
