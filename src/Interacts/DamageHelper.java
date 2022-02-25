package Interacts;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class DamageHelper {

    private static DamageHelper damageHelper;

    public static DamageHelper Helper() {
        if(damageHelper == null) damageHelper = new DamageHelper();
        return damageHelper;
    }

    int iceDamageCalculate(int iceDamage, LivingEntity master, LivingEntity target) {
        return 0;
    }

    int elecDamageCalculate(int elecDamage, LivingEntity master, LivingEntity target) {
        return 0;
    }

    int windDamageCalculate(int windDamage, LivingEntity master, LivingEntity target) {
        return 0;
    }
}
