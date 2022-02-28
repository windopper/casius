package Scheduler;

import Data.Core;
import Data.CoreData;
import org.bukkit.entity.LivingEntity;

public class RealTimeLoop {
    private static RealTimeLoop RealTimeLoop;
    private RealTimeLoop() {

    }
    public static RealTimeLoop getInstance() {
        if(RealTimeLoop == null) RealTimeLoop = new RealTimeLoop();
        return RealTimeLoop;
    }
    public void updateHealth() {
        for(LivingEntity entity : Core.getRegisteredEntity()) {
            CoreData coreData = Core.getData(entity);

            double currentHealth = coreData.currentHealth;
            double health = coreData.health;

            if(currentHealth <= health) {
                entity.setHealth(currentHealth / health);
            } else {
                entity.setHealth(entity.getMaxHealth());
            }
        }
    }
}
