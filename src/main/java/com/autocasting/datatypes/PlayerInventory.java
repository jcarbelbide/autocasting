package com.autocasting.datatypes;


import lombok.Getter;
import lombok.Setter;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
public class PlayerInventory
{
	@Getter
	@Setter
	private InfiniteRuneItem mainHand;

	@Getter
	@Setter
	private InfiniteRuneItem offHand;

	@Getter
	@Setter
	private Pouch pouch;

	@Getter
	@Setter
	private List<RuneItemQuantity> runes;

	public PlayerInventory()
	{
		mainHand = null;
		offHand = null;
		pouch = null;
		runes = new ArrayList<>();
	}

	public void clearItems()
	{
		mainHand = null;
		offHand = null;
	}

	public void addRuneStack(RuneItemQuantity runeStack)
	{
		runes.add(runeStack);
	}

	public void clearRunes()
	{
		runes = new ArrayList<>();
	}
}
