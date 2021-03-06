package Utils;

import Data.CoreData;
import Data.DamageRecord;
import Data.PlayerCoreData;
import Indicates.DamageIndicates;
import Interacts.Evasions;
import Mission.MissionPlayerKill;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;

public class DamageUtils {

    public static void giveDamage(CoreData<? extends LivingEntity> masterCoreData, CoreData<? extends LivingEntity> targetCoreData, double iceDamageRate,
                                  double elecDamageRate, double windDamageRate) {
        giveDamage(masterCoreData, targetCoreData, iceDamageRate, elecDamageRate, windDamageRate, "Natural");
    }

    public static void giveDamage(CoreData<? extends LivingEntity> masterCoreData, CoreData<? extends LivingEntity> targetCoreData, double iceDamageRate,
                                  double elecDamageRate, double windDamageRate, String causeElement) {

        LivingEntity master = masterCoreData.master;
        LivingEntity target = targetCoreData.master;

        if(masterCoreData.isInvulnerable) return;
        if(targetCoreData.isInvulnerable) return;

        // 타겟이 회피가 가능하면
        if(Evasions.isEvadable(targetCoreData)) {
            return;
        }

        DamageIndicates damageIndicates = DamageIndicates.getBuilder(target.getEyeLocation());

        int masterMinIceDamage = masterCoreData.minIcedmg;
        int masterMaxIceDamage = masterCoreData.maxIcedmg;
        int masterMinElecDamage = masterCoreData.minElecdmg;
        int masterMaxElecDamage = masterCoreData.maxElecdmg;
        int masterMinWindDamage = masterCoreData.minWinddmg;
        int masterMaxWindDamage = masterCoreData.maxWinddmg;
        int masterIceDamageVar = masterCoreData.iceDmg;
        int masterElecDamageVar = masterCoreData.elecDmg;
        int masterWindDamageVar = masterCoreData.windDmg;
        int targetIceDefenseVar = targetCoreData.iceDef;
        int targetElecDefenseVar = targetCoreData.elecDef;
        int targetWindDefenseVar = targetCoreData.windDef;

        double tempDamageTakenModfRate = targetCoreData.getRateTempDamageTakenModf();
        double tempDamageGivenModfRate = masterCoreData.getRateTempDamageGivenModf();

        int masterRandomIceDamage = NumberUtils.randomInt(masterMinIceDamage, masterMaxIceDamage);
        int masterRandomElecDamage = NumberUtils.randomInt(masterMinElecDamage, masterMaxElecDamage);
        int masterRandomWindDamage = NumberUtils.randomInt(masterMinWindDamage, masterMaxWindDamage);

        int masterFinalIceDamage = (int) (damageCalculate(masterRandomIceDamage, masterIceDamageVar, targetIceDefenseVar) *
                        iceDamageRate * tempDamageTakenModfRate * tempDamageGivenModfRate);
        int masterFinalElecDamage = (int) (damageCalculate(masterRandomElecDamage, masterElecDamageVar, targetElecDefenseVar) *
                        elecDamageRate * tempDamageTakenModfRate * tempDamageGivenModfRate);
        int masterFinalWindDamage = (int) (damageCalculate(masterRandomWindDamage, masterWindDamageVar, targetWindDefenseVar) *
                        windDamageRate * tempDamageTakenModfRate * tempDamageGivenModfRate);


        if(masterFinalIceDamage != 0) damageIndicates.addIceDamage(masterFinalIceDamage);
        if(masterFinalElecDamage != 0) damageIndicates.addElecDamage(masterFinalElecDamage);
        if(masterFinalWindDamage != 0) damageIndicates.addWindDamage(masterFinalWindDamage);
        damageIndicates.build();

        target.setMaximumNoDamageTicks(1);
        target.setNoDamageTicks(0);
        target.damage(0.0001);

        ParticleUtils.showParticle(Particle.BLOCK_CRACK, target.getLocation().add(0, 1, 0), 20, 0.5, 0.5, 0.5, 0, Material.REDSTONE_BLOCK.createBlockData());

        targetCoreData.currentHealth = targetCoreData.currentHealth - masterFinalWindDamage - masterFinalElecDamage - masterFinalIceDamage;

        /** MISSION */
        if(targetCoreData.currentHealth <= 0 && masterCoreData instanceof PlayerCoreData playerCoreData && playerCoreData.privateMission instanceof MissionPlayerKill missionPlayerKill) {
            missionPlayerKill.updateMission();
        }

        /** RECORD */
        targetCoreData.takenDamages.add(new DamageRecord(master.getName(), causeElement, System.currentTimeMillis(), masterFinalWindDamage + masterFinalElecDamage + masterFinalIceDamage));
    }

    public static void giveDamage(CoreData<? extends LivingEntity> masterCoreData, CoreData<? extends LivingEntity> targetCoreData, double damageRate) {
        giveDamage(masterCoreData, targetCoreData, damageRate, damageRate, damageRate);
    }

    public static int damageCalculate(int elementDamage, int masterElementDamageVar, int targetElementDefenseVar) {
        return (int) (elementDamage * defenseToRate(targetElementDefenseVar) * damageToRate(masterElementDamageVar));
    }

    public static double defenseToRate(int def) {
        double value = 1000;
        if(def <= 1000) {
            value -= def;
            value /= 1000;
        }
        else {
            value = 0;
        }
        return value;
    }

    public static double damageToRate(int dmg) {
        double value = 1000;
        if(dmg >= -1000) {
            value += dmg;
            value /= 1000;
        }
        return value;
    }
}
