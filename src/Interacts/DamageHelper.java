package Interacts;

import Data.Core;
import Data.CoreData;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class DamageHelper {

    private static DamageHelper damageHelper;

    public static DamageHelper Helper() {
        if(damageHelper == null) damageHelper = new DamageHelper();
        return damageHelper;
    }

    int iceDamageCalculate(int iceDamage, LivingEntity master, LivingEntity target) {

        CoreData masterData = Core.getData(master);
        CoreData targetData = Core.getData(target);

        double iceDmgRate = masterData.dmgHelper(masterData.iceDmg);
        double iceDefRate = targetData.defHelper(targetData.iceDef);

        iceDamage = (int) (iceDamage * iceDmgRate * iceDefRate);
        return iceDamage;
    }

    int elecDamageCalculate(int elecDamage, LivingEntity master, LivingEntity target) {

        CoreData masterData = Core.getData(master);
        CoreData targetData = Core.getData(target);

        double elecDmgRate = masterData.dmgHelper(masterData.elecDmg);
        double elecDefRate = targetData.defHelper(targetData.elecDef);

        elecDamage = (int) (elecDamage * elecDefRate * elecDmgRate);
        return elecDamage;
    }

    int windDamageCalculate(int windDamage, LivingEntity master, LivingEntity target) {

        CoreData masterData = Core.getData(master);
        CoreData targetData = Core.getData(target);

        double windDmgRate = masterData.dmgHelper(masterData.windDmg);
        double windDefRate = targetData.defHelper(targetData.windDef);

        windDamage = (int) (windDamage * windDefRate * windDmgRate);
        return windDamage;
    }
}
