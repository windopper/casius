package Utils;

import Data.CoreData;
import Indicates.DamageIndicates;
import org.bukkit.entity.LivingEntity;

public class DamageUtil {

    public static void giveDamage(CoreData masterCoreData, CoreData targetCoreData, double iceDamageRate,
                                  double elecDamageRate, double windDamageRate) {

        LivingEntity master = masterCoreData.master;
        LivingEntity target = targetCoreData.master;

        DamageIndicates damageIndicates = DamageIndicates.getBuilder(target.getEyeLocation());

        // 타겟이 회피가 가능하면
        if(targetCoreData.evasions.size() > 0) {
            targetCoreData.evasions.remove();
            damageIndicates.addEvasion().build();
            return;
        }

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

        int masterRandomIceDamage = NumberUtil.randomInt(masterMinIceDamage, masterMaxIceDamage);
        int masterRandomElecDamage = NumberUtil.randomInt(masterMinElecDamage, masterMaxElecDamage);
        int masterRandomWindDamage = NumberUtil.randomInt(masterMinWindDamage, masterMaxWindDamage);

        int masterFinalIceDamage = (int) (damageCalculate(masterRandomIceDamage, masterIceDamageVar, targetIceDefenseVar) *
                        iceDamageRate);
        int masterFinalElecDamage = (int) (damageCalculate(masterRandomElecDamage, masterElecDamageVar, targetElecDefenseVar) *
                        elecDamageRate);
        int masterFinalWindDamage = (int) (damageCalculate(masterRandomWindDamage, masterWindDamageVar, targetWindDefenseVar) *
                        windDamageRate);


        if(masterFinalIceDamage != 0) damageIndicates.addIceDamage(masterFinalIceDamage);
        if(masterFinalElecDamage != 0) damageIndicates.addElecDamage(masterFinalElecDamage);
        if(masterFinalWindDamage != 0) damageIndicates.addWindDamage(masterFinalWindDamage);
        damageIndicates.build();

        target.setMaximumNoDamageTicks(1);
        target.setNoDamageTicks(1);
        target.damage(0.0001);

    }

    public static void giveDamage(CoreData masterCoreData, CoreData targetCoreData, double damageRate) {
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
