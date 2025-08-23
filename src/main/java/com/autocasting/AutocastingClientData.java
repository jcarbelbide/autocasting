package com.autocasting;

import com.autocasting.datatypes.Spell;
import net.runelite.api.*;
import net.runelite.api.gameval.InventoryID;
import net.runelite.api.gameval.VarbitID;

import org.apache.commons.lang3.ArrayUtils;

import javax.inject.Inject;

public class AutocastingClientData
{
	@Inject
	private Client client;

	public int getAutocastVarbit()
	{
		return client.getVarbitValue(VarbitID.AUTOCAST_SPELL);
	}

	public Spell getAutocastSpell()
	{
		int varbitValue = getAutocastVarbit();
		return Spell.getSpell(varbitValue);
	}

	public int getWeaponTypeId()
	{
		return client.getVarbitValue(VarbitID.COMBAT_WEAPON_CATEGORY);
	}

	// Based off StatusBarsPlugin.java
	public boolean isInCombat()
	{
		final Player localPlayer = client.getLocalPlayer();
		if (localPlayer == null)
		{
			return false;
		}
		final Actor interacting = localPlayer.getInteracting();
		boolean fightingNPC = interacting instanceof NPC && ArrayUtils.contains(((NPC) interacting).getComposition().getActions(), "Attack");
		boolean fightingPlayer = interacting instanceof Player && client.getVarbitValue(VarbitID.PVP_AREA_CLIENT) == 1;
		return fightingNPC || fightingPlayer;
	}

	public int getGameTick()
	{
		return client.getTickCount();
	}

	public int fps()
	{
		return client.getFPS();
	}

	public EnumComposition getRunepouchEnum()
	{
		return client.getEnum(EnumID.RUNEPOUCH_RUNE);
	}

	public int varbitValue(int varbit)
	{
		return client.getVarbitValue(varbit);
	}

	public ItemContainer getInventory()
	{
		return client.getItemContainer(InventoryID.INV);
	}

	public ItemContainer getEquipment()
	{
		return client.getItemContainer(InventoryID.WORN);
	}
}
