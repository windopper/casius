package Ability;

import Data.CoreData;
import Data.PlayerCoreData;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public abstract class Ability {

    public int elecDmg = 0;
    public int iceDmg = 0;
    public int windDmg = 0;

    public final AbilitySlot abilitySlot;

    public final String skillName;
    public double fireRadius = 1.5;
    public final double coolDown;
    public final int energyRequire;

    public Ability(String skillName, AbilitySlot slot, double coolDown, int energyRequire) {
        this.skillName = skillName;
        this.abilitySlot = slot;
        this.coolDown = coolDown;
        this.energyRequire = energyRequire;
    }

    public ResultCode run(PlayerCoreData playerCoreData) {

        // 발동 가능 조건 충족하는지 확인
        ResultCode condition = checkCondition(playerCoreData);
        if(condition != ResultCode.SUCCESS) return condition;

        // 필요한 자원 사용
        preInvokeAbility(playerCoreData);

        // 능력 발동
        invokeAbility(playerCoreData);

        return ResultCode.SUCCESS;
    }

    public ResultCode checkCondition(PlayerCoreData playerCoreData) {

        if(playerCoreData == null) return ResultCode.NULL_COREDATA;

        if(playerCoreData.currentEnergy < energyRequire) {
            return ResultCode.ENERGY_SHORTAGE;
        }
        if(playerCoreData.coolDowns[abilitySlot.ordinal()] > 0) {
            return ResultCode.ON_COOLDOWN;
        }
        return ResultCode.SUCCESS;
    }

    public void preInvokeAbility(PlayerCoreData playerCoreData) {
        assert playerCoreData != null;
        playerCoreData.currentEnergy -= energyRequire;
        playerCoreData.coolDowns[abilitySlot.ordinal()] = coolDown;
    }

    public abstract void invokeAbility(PlayerCoreData playerCoreData);

    public abstract void abilityDesign(Location location);

    public abstract void abilityEffect(CoreData<? extends LivingEntity> targetCoreData);

    public String getSkillName() { return skillName; }
}