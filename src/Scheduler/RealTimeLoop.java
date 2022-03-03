package Scheduler;

import Data.CoreData;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;


public class RealTimeLoop {

    private static RealTimeLoop RealTimeLoop;
    private RealTimeLoop() {

    }

    public static RealTimeLoop getInstance() {
        if(RealTimeLoop == null) RealTimeLoop = new RealTimeLoop();
        return RealTimeLoop;
    }

    // 1틱마다 한번
    public void updateDisplayHealth(CoreData coreData) {

        LivingEntity entity = coreData.master;

        if(entity instanceof Player player) {
            if(!player.isOnline()) {
                return;
            }
        }

        double currentHealth = coreData.currentHealth;
        double health = coreData.health;

        if(currentHealth <= health) {
            entity.setHealth(currentHealth / health * entity.getMaxHealth());
        } else {
            entity.setHealth(entity.getMaxHealth());
        }
    }

    // 2틱마다 한번
    public void updateCoolDown(CoreData coreData) {
        if(coreData == null) return;

        for(int i=0; i<coreData.coolDowns.length; i++) {
            double C = coreData.coolDowns[i];
            if(C - 0.1 < 0) coreData.coolDowns[i] = 0;
            else coreData.coolDowns[i] -= 0.1;
        }
    }

    // 1초마다 한번
    public void updateRegenerations(CoreData coreData) {
        if(coreData == null) return;
        coreData.addEnergy(coreData.energyRegen);
        coreData.addHealth(coreData.healthRegen);
    }
}
