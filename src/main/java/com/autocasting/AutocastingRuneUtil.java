package com.autocasting;

import com.autocasting.datatypes.*;

import net.runelite.api.EnumComposition;
import net.runelite.api.EquipmentInventorySlot;
import net.runelite.api.Item;
import net.runelite.api.ItemContainer;

import javax.inject.Inject;
import java.util.EnumMap;
import java.util.Map;

public class AutocastingRuneUtil
{
	@Inject
	private AutocastingClientData clientData;

	public PlayerInventory updateInventory(PlayerInventory current)
	{
		PlayerInventory updated = new PlayerInventory();
		if (current != null)
		{
			updated.setMainHand(current.getMainHand());
			updated.setOffHand(current.getOffHand());
		}

		ItemContainer inventory = clientData.getInventory();
		if (inventory == null)
		{
			return updated;
		}

		for (Item item : inventory.getItems())
		{
			int itemId = item.getId();
			int quantity = item.getQuantity();

			Pouch pouch = Pouch.getPouch(itemId);
			if (pouch != null)
			{
				updated.setPouch(pouch);
			}

			RuneItem rune = RuneItem.getRuneItem(itemId);
			if (rune != null)
			{
				updated.addRuneStack(new RuneItemQuantity(rune, quantity));
			}
		}
		return updated;
	}

	public PlayerInventory updateEquipment(PlayerInventory current)
	{
		PlayerInventory updated = new PlayerInventory();
		if (current != null)
		{
			updated.setRunes(current.getRunes());
			updated.setPouch(current.getPouch());
		}

		ItemContainer equipment = clientData.getEquipment();
		Item weaponSlot = equipment.getItem(EquipmentInventorySlot.WEAPON.getSlotIdx());
		Item shieldSlot = equipment.getItem(EquipmentInventorySlot.SHIELD.getSlotIdx());

		InfiniteRuneItem mainHand = weaponSlot == null ? null : InfiniteRuneItem.getInfiniteRuneItem(weaponSlot.getId());
		updated.setMainHand(mainHand);

		InfiniteRuneItem offHand = shieldSlot == null ? null : InfiniteRuneItem.getInfiniteRuneItem(shieldSlot.getId());
		updated.setOffHand(offHand);

		return updated;
	}

	public Map<RuneType, Integer> availableRunes(PlayerInventory player)
	{
		Map<RuneType, Integer> runes = new EnumMap<>(RuneType.class);

		for (RuneItemQuantity item : player.getRunes())
		{
			for (RuneType t : item.getRune().getMatchingTypes())
			{
				runes.merge(t, item.getQuantity(), Integer::sum);
			}
		}

		if (player.getPouch() != null)
		{
			int pouchSize = 4;
			switch (player.getPouch().getPouchType())
			{
				case THREE_SLOTS:
					pouchSize = 3;
				case FOUR_SLOTS:
					EnumComposition runepouchEnum = clientData.getRunepouchEnum();
					for (int i = 0; i < pouchSize; i++)
					{
						int runepouchId = clientData.varbitValue(AutocastingConstants.VARBIT_RUNE_POUCH_RUNES[i]);
						int amount = clientData.varbitValue(AutocastingConstants.VARBIT_RUNE_POUCH_AMOUNTS[i]);
						if (runepouchId == 0 || amount <= 0)
						{
							continue;
						}
						int runeId = runepouchEnum.getIntValue(runepouchId);
						RuneItem rune = RuneItem.getRuneItem(runeId);
						if (rune != null)
						{
							for (RuneType t : rune.getMatchingTypes())
							{
								runes.merge(t, amount, Integer::sum);
							}
						}
					}
					break;
				case LMS:
					runes.put(RuneType.WATER, Integer.MAX_VALUE);
					runes.put(RuneType.DEATH, Integer.MAX_VALUE);
					runes.put(RuneType.BLOOD, Integer.MAX_VALUE);
					runes.put(RuneType.SOUL, Integer.MAX_VALUE);
				case INFINITE:
					for (RuneType t : RuneType.values())
					{
						runes.put(t, Integer.MAX_VALUE);
					}
			}
		}

		InfiniteRuneItem weapon = player.getMainHand();
		if (weapon != null)
		{
			for (RuneType rune : weapon.getMatchingTypes())
			{
				runes.put(rune, Integer.MAX_VALUE);
			}
		}

		InfiniteRuneItem offHand = player.getOffHand();
		if (offHand != null)
		{
			for (RuneType rune : offHand.getMatchingTypes())
			{
				runes.put(rune, Integer.MAX_VALUE);
			}
		}

		return runes;
	}

	public int calculateCastsRemaining(Spell spell, Map<RuneType, Integer> runes)
	{
		if (clientData.varbitValue(AutocastingConstants.VARBIT_FOUNTAIN_OF_RUNES) == 1)
		{
			return Integer.MAX_VALUE;
		}

		RuneTypeQuantity[] cost = spell.getSpellCost().getRuneCost();

		int lowestCasts = Integer.MAX_VALUE;
		for (RuneTypeQuantity rune : cost)
		{
			RuneType runeType = rune.getRuneType();
			int specificRuneCost = rune.getQuantity();

			Integer amountHeld = runes.getOrDefault(runeType, null);
			if (amountHeld != null)
			{
				// Do division unless we have "infinite", as denoted with MAX_VALUE
				int casts = amountHeld == Integer.MAX_VALUE ? Integer.MAX_VALUE : amountHeld / specificRuneCost;
				lowestCasts = Math.min(casts, lowestCasts);
			}
			else
			{
				// We don't have any of that type of rune
				return 0;
			}
		}
		return lowestCasts;
	}
}
