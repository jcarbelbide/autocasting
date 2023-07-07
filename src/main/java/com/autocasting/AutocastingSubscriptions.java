package com.autocasting;

import com.autocasting.datatypes.Spell;

import net.runelite.api.Client;
import net.runelite.api.InventoryID;
import net.runelite.api.Skill;
import net.runelite.api.Varbits;
import net.runelite.api.events.*;
import net.runelite.client.eventbus.Subscribe;
import org.apache.commons.lang3.ArrayUtils;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AutocastingSubscriptions
{
	@Inject
	private Client client;

	@Inject
	private AutocastingConfig config;

	@Inject
	private AutocastingNotifications notifications;

	@Inject
	private AutocastingState state;

	@Inject
	private AutocastingClientData clientData;

	private boolean updateRunesPostClientTick = false;

	@Subscribe
	public void onVarbitChanged(VarbitChanged event)
	{
		int varbitId = event.getVarbitId();
		boolean isRelevantAutocastVarbit =
			varbitId == AutocastingConstants.VARBIT_AUTOCAST_SPELL
				|| varbitId == Varbits.EQUIPPED_WEAPON_TYPE;
		if (isRelevantAutocastVarbit)
		{
			state.updateAutocastSpell();
			state.updateIsEquippedWeaponMagic();
		}

		boolean isRelevantRunePouchVarbit =
			ArrayUtils.contains(AutocastingConstants.VARBIT_RUNE_POUCH_RUNES, varbitId)
				|| ArrayUtils.contains(AutocastingConstants.VARBIT_RUNE_POUCH_AMOUNTS, varbitId);
		if (isRelevantRunePouchVarbit)
		{
			updateRunesPostClientTick = true;
		}

		if (varbitId == AutocastingConstants.VARBIT_FOUNTAIN_OF_RUNES)
		{
			state.updateCastsRemaining(false);
		}
	}

	@Subscribe
	public void onStatChanged(StatChanged event)
	{
		if (event.getSkill().getName().equals(Skill.MAGIC.getName()))
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
