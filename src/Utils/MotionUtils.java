package Utils;

import Data.CoreData;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

public class MotionUtils {
    public static void playMotion(Vector direction, double power, CoreData<? extends LivingEntity> targetCoreData) {
        if(targetCoreData.unstoppable) return;
        LivingEntity target = targetCoreData.master;
        Vector v = direction.normalize();
        v.multiply(power);
        target.setVelocity(v);
    }
    public static void playMotion(Vector direction, double power, CoreData<? extends LivingEntity> masterCoreDat, CoreData<? extends LivingEntity> targetCoreData) {
        playMotion(direction, power, targetCoreData);
    }
}
