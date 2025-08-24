package com.autocasting;

import net.runelite.api.Skill;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.gameval.VarbitID;
import net.runelite.api.events.*;
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
			case VarbitID.AUTOCAST_SPELL:
				state.setRecentlySentNoSpellSelectedNotification(false);
			case VarbitID.COMBAT_WEAPON_CATEGORY:
				state.updateIsEquippedWeaponMagic();
				state.updateAutocastSpell();
				break;
			case VarbitID.RUNE_POUCH_TYPE_1:
			case VarbitID.RUNE_POUCH_TYPE_2:
			case VarbitID.RUNE_POUCH_TYPE_3:
			case VarbitID.RUNE_POUCH_TYPE_4:
			case VarbitID.RUNE_POUCH_TYPE_5:
			case VarbitID.RUNE_POUCH_TYPE_6:
			case VarbitID.RUNE_POUCH_QUANTITY_1:
			case VarbitID.RUNE_POUCH_QUANTITY_2:
			case VarbitID.RUNE_POUCH_QUANTITY_3:
			case VarbitID.RUNE_POUCH_QUANTITY_4:
			case VarbitID.RUNE_POUCH_QUANTITY_5:
			case VarbitID.RUNE_POUCH_QUANTITY_6:
				updateRunesPostClientTick = true;
				break;
			case VarbitID.FOUNTAIN_OF_RUNE_ACTIVE:
				state.updateCastsRemaining(false);
				break;
		}
	}

	@Subscribe
	public void onWidgetLoaded(WidgetLoaded event)
	{
		switch (event.getGroupId())
		{
			case InterfaceID.BANKMAIN:
			case InterfaceID.BANKSIDE:
			case InterfaceID.BANK_DEPOSITBOX:
			case InterfaceID.SHARED_BANK:
			case InterfaceID.SHARED_BANK_SIDE:
				state.setBanking(true);
				state.setRecentlySentNoSpellSelectedNotification(false);
		}
	}

	@Subscribe
	public void onWidgetClosed(WidgetClosed event)
	{
		switch (event.getGroupId())
		{
			case InterfaceID.BANKMAIN:
			case InterfaceID.BANKSIDE:
			case InterfaceID.BANK_DEPOSITBOX:
			case InterfaceID.SHARED_BANK:
			case InterfaceID.SHARED_BANK_SIDE:
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
		if (event.getContainerId() == InventoryID.WORN)
		{
			// Equipped items changed; should check to see if an infinite rune item is equipped
			state.updateInfiniteRuneSources();
			state.updateIsBlacklisted();
		}
		if (event.getContainerId() == InventoryID.INV)
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
