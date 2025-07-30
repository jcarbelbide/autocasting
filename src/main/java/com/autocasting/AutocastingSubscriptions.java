package com.autocasting;

import net.runelite.api.InventoryID;
import net.runelite.api.Skill;
import net.runelite.api.Varbits;
import net.runelite.api.events.*;
import net.runelite.api.widgets.InterfaceID;
import net.runelite.client.eventbus.Subscribe;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AutocastingSubscriptions
{
	@Inject
	private AutocastingState state;

	private boolean updateRunesPostClientTick = false;

	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{
		switch (event.getVarbitId())
		{
			case AutocastingConstants.VARBIT_AUTOCAST_SPELL:
				state.setRecentlySentNoSpellSelectedNotification(false);
			case Varbits.EQUIPPED_WEAPON_TYPE:
				state.updateIsEquippedWeaponMagic();
				state.updateAutocastSpell();
				break;
			case Varbits.RUNE_POUCH_RUNE1:
			case Varbits.RUNE_POUCH_RUNE2:
			case Varbits.RUNE_POUCH_RUNE3:
			case Varbits.RUNE_POUCH_RUNE4:
			case Varbits.RUNE_POUCH_AMOUNT1:
			case Varbits.RUNE_POUCH_AMOUNT2:
			case Varbits.RUNE_POUCH_AMOUNT3:
			case Varbits.RUNE_POUCH_AMOUNT4:
				updateRunesPostClientTick = true;
				break;
			case AutocastingConstants.VARBIT_FOUNTAIN_OF_RUNES:
				state.updateCastsRemaining(false);
				break;
		}
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded event)
	{
		switch (event.getGroupId())
		{
			case InterfaceID.BANK:
			case InterfaceID.BANK_INVENTORY:
			case InterfaceID.DEPOSIT_BOX:
			case InterfaceID.GROUP_STORAGE:
			case InterfaceID.GROUP_STORAGE_INVENTORY:
				state.setBanking(true);
				state.setRecentlySentNoSpellSelectedNotification(false);
		}
	}

	@Subscribe
	public void onWidgetClosed(WidgetClosed event)
	{
		switch (event.getGroupId())
		{
			case InterfaceID.BANK:
			case InterfaceID.BANK_INVENTORY:
			case InterfaceID.DEPOSIT_BOX:
			case InterfaceID.GROUP_STORAGE:
			case InterfaceID.GROUP_STORAGE_INVENTORY:
				state.setBanking(false);
		}
	}

	@Subscribe
	public void onStatChanged(StatChanged event)
	{
		if (event.getSkill() == Skill.MAGIC)
		{
			// Now need to check if new boostedLevel is still high enough for the autocast spell
			int boostedLevel = event.getBoostedLevel();
			state.updateMagicLevel(boostedLevel);
		}
	}

	@Subscribe
	public void onItemContainerChanged(ItemContainerChanged event)
	{
		if (event.getContainerId() == InventoryID.EQUIPMENT.getId())
		{
			// Equipped items changed; should check to see if an infinite rune item is equipped
			state.updateInfiniteRuneSources();
		}
		if (event.getContainerId() == InventoryID.INVENTORY.getId())
		{
			// Inventory changed; should re-count runes
			state.updateRunes();
		}
	}

	@Subscribe
	public void onPostClientTick(PostClientTick event)
	{
		// After everything settles, final rune counts should definitely be accurate so let's count
		if (updateRunesPostClientTick)
		{
			state.updateRunes();
			updateRunesPostClientTick = false;
		}
	}

	@Subscribe
	public void onGameTick(GameTick gameTick)
	{
		// This only updates every tick, so we shouldn't be re-computing while rendering each frame
		state.updateCombatStatus();
	}
}
