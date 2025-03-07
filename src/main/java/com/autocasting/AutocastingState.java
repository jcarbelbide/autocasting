package com.autocasting;

import com.autocasting.datatypes.PlayerInventory;
import com.autocasting.datatypes.RuneType;
import com.autocasting.datatypes.Spell;
import com.autocasting.dependencies.attackstyles.WeaponType;

import lombok.Getter;
import lombok.Setter;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class AutocastingState
{
	@Inject
	private AutocastingClientData clientData;

	@Inject
	private AutocastingRuneUtil runeUtil;

	@Inject
	private AutocastingNotifications notifications;

	@Getter
	@Setter
	private boolean magicLevelTooLowForSpell;

	@Getter
	@Setter
	private boolean isEquippedWeaponMagic;

	@Getter
	@Setter
	private WeaponType currentWeaponType;

	@Getter
	@Setter
	private Spell currentAutocastSpell;

	@Getter
	@Setter
	private PlayerInventory playerInventory;

	@Getter
	@Setter
	private boolean consideredInCombat;

	@Getter
	@Setter
	private int lastCombatTick;

	@Getter
	@Setter
	private Map<RuneType, Integer> availableRunes;

	@Getter
	@Setter
	private int castsRemaining;

	@Getter
	@Setter
	private boolean banking;

	public void updateAutocastSpell()
	{
		// Get new autocast spell.
		Spell newAutocastSpell = clientData.getAutocastSpell();
		if (newAutocastSpell == null)
		{
			return;
		}
		// Some input (weapon change, attack style change, or autocast change) happened, so magic level must be fine now
		setMagicLevelTooLowForSpell(false);
		// If the new spell is not null, and there is currently no autocast spell selected, update it
		if (currentAutocastSpell == null || newAutocastSpell.getVarbitValue() != currentAutocastSpell.getVarbitValue())
		{
			currentAutocastSpell = newAutocastSpell;
		}
		updateCastsRemaining(true);
	}

	public boolean hasActiveAutocast()
	{
		return currentAutocastSpell != null && currentAutocastSpell != Spell.NO_SPELL;
	}

    /*
    Calculating casts remaining is a 3 stage process, first we check inventory, equipment, and rune pouch for relevant items
    These get set on PlayerInventory. Subscription hooks will call stage 1 methods - updateRunes, updateInfiniteRuneSources
    At the end of both functions the second stage is called which computes total runes based on all factors.
    Finally based on current autocast spell and stage 2 results we can math out the number of casts available
    */

	public void updateRunes()
	{
		setPlayerInventory(runeUtil.updateInventory(playerInventory));
		calculateNetRuneTypes();
	}

	public void updateInfiniteRuneSources()
	{
		setPlayerInventory(runeUtil.updateEquipment(playerInventory));
		calculateNetRuneTypes();
	}

	private void calculateNetRuneTypes()
	{
		setAvailableRunes(runeUtil.availableRunes(playerInventory));
		updateCastsRemaining(false);
	}

	public void updateCastsRemaining(boolean autocastSpellUpdate)
	{
		if (currentAutocastSpell == null || currentAutocastSpell == Spell.NO_SPELL)
		{
			castsRemaining = 0;
			return;
		}

		int newCastsRemaining = runeUtil.calculateCastsRemaining(currentAutocastSpell, availableRunes);
		if (!autocastSpellUpdate && castsRemaining != newCastsRemaining)
		{
			// The number updated, so let's go through the configs and see if we need to send notifications
			notifications.handleCastsUpdated(castsRemaining, newCastsRemaining);
		}
		castsRemaining = newCastsRemaining;
	}

	public void updateIsEquippedWeaponMagic()
	{
		int weaponTypeID = clientData.getWeaponTypeId();
		WeaponType newWeaponType = WeaponType.getWeaponType(weaponTypeID);
		if (newWeaponType == currentWeaponType)
		{
			return;
		}
		currentWeaponType = newWeaponType;

		isEquippedWeaponMagic = newWeaponType == WeaponType.TYPE_18 ||
								newWeaponType == WeaponType.TYPE_21 ||
								newWeaponType == WeaponType.TYPE_22;

		// The below types do have a casting option, but do not autocast spells, so leave them out.
		// TYPE_6: These are salamanders. They do not autocast, but give magic xp, so technically have a "casting" option.
		// TYPE_23: Trident, Sanguinesti, etc. Do not have autocast options, so do not show overlay when these are equipped.
	}

	public void updateMagicLevel(int boostedLevel)
	{
		Spell autocastSpell = Spell.getSpell(clientData.getAutocastVarbit());

		if (boostedLevel < autocastSpell.getLevelRequirement())
		{
			// We don't need to send new messages or update state if it didn't actually change
			if (!magicLevelTooLowForSpell)
			{
				magicLevelTooLowForSpell = true;
				notifications.notifyStatDrain();
			}
		}
		else
		{
			magicLevelTooLowForSpell = false;
		}
	}

	public void updateCombatStatus()
	{
		boolean inCombat = clientData.isInCombat();
		int currentTick = clientData.getGameTick();
		if (inCombat)
		{
			lastCombatTick = currentTick;
			consideredInCombat = true;
		}
		else
		{
			consideredInCombat = currentTick - lastCombatTick <= AutocastingConstants.OUT_OF_COMBAT_TICK_DELAY;
		}
	}
}
