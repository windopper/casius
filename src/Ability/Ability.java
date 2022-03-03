package Ability;

import Data.Core;
import Data.CoreData;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public abstract class Ability {

    public int elecDmg = 0;
    public int iceDmg = 0;
    public int windDmg = 0;

    public final AbilitySlot abilitySlot;

    public double fireRadius = 1.5;
    public final double coolDown;
    public final int energyRequire;

    public Ability(AbilitySlot slot, double coolDown, int energyRequire) {
        this.abilitySlot = slot;
        this.coolDown = coolDown;
        this.energyRequire = energyRequire;
    }

    public void run(LivingEntity livingEntity) {

        checkCondition(livingEntity);

        preInvokeAbility(livingEntity);

        invokeAbility(livingEntity);

    }

    public boolean checkCondition(LivingEntity livingEntity) {
        CoreData coreData = Core.getData(livingEntity);

        if(coreData == null) return false;

        if(coreData.currentEnergy < energyRequire) {
            return false;
        }
        if(coreData.coolDowns[abilitySlot.ordinal()] > 0) {
            return false;
        }
        return true;
    }

    public void preInvokeAbility(LivingEntity livingEntity) {
        CoreData coreData = Core.getData(livingEntity);
        assert coreData != null;
        coreData.currentEnergy -= energyRequire;
        coreData.coolDowns[abilitySlot.ordinal()] = coolDown;
    }

    public abstract void invokeAbility(LivingEntity livingEntity);

    public abstract void abilityDesign(Location location);

    public abstract void abilityEffect(LivingEntity target);
}