package Ability;

import Data.Core;
import Data.CoreData;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

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

    public void run(CoreData coreData) {

        // 발동 가능 조건 충족하는지 확인
        checkCondition(coreData);

        // 필요한 자원 사용
        preInvokeAbility(coreData);

        // 능력 발동
        invokeAbility(coreData);

    }

    public boolean checkCondition(CoreData coreData) {

        if(coreData == null) return false;

        if(coreData.currentEnergy < energyRequire) {
            return false;
        }
        if(coreData.coolDowns[abilitySlot.ordinal()] > 0) {
            return false;
        }
        return true;
    }

    public void preInvokeAbility(CoreData coreData) {
        assert coreData != null;
        coreData.currentEnergy -= energyRequire;
        coreData.coolDowns[abilitySlot.ordinal()] = coolDown;
    }

    public abstract void invokeAbility(CoreData coreData);

    public abstract void abilityDesign(Location location);

    public abstract void abilityEffect(LivingEntity target);
}