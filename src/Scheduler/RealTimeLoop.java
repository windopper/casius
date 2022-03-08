package Scheduler;

import Ability.Ability;
import Data.PlayerCoreData;
import KeyBinds.KeyDisplay;
import org.bukkit.Sound;
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
    public void updateDisplayHealth(PlayerCoreData playerCoreData) {

        LivingEntity entity = playerCoreData.master;

        if(entity instanceof Player player) {
            if(!player.isOnline()) {
                return;
            }
        }

        double currentHealth = playerCoreData.currentHealth;
        double health = playerCoreData.health;

        if(currentHealth <= 0) {

        } else if(currentHealth <= health) {
            entity.setHealth(currentHealth / health * entity.getMaxHealth());
        } else {
            entity.setHealth(entity.getMaxHealth());
        }
    }

    public void updateDisplayEnergy(PlayerCoreData playerCoreData) {
        LivingEntity entity = playerCoreData.master;

        if(entity instanceof Player player) {
            if(!player.isOnline()) return;

            double currentEnergy = playerCoreData.currentEnergy;
            double energy = playerCoreData.energy;

            if(currentEnergy <= energy && currentEnergy >= 0) {
                player.setFoodLevel((int) (currentEnergy / energy * 20));
            }


        }


    }

    // 2틱마다 한번
    public void updateCoolDown(PlayerCoreData playerCoreData) {
        if(playerCoreData == null) return;
        Player master = playerCoreData.master;

        for(int i = 0; i< playerCoreData.coolDowns.length; i++) {
            double C = playerCoreData.coolDowns[i];

            if(C - 0.2 < 0 && C - 0.1 >= 0) {
                Ability ability = playerCoreData.abilities[i];
                if(ability == null) continue;
                try {
                    master.playSound(master.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1, 1);
                    KeyDisplay.getInstance(playerCoreData).updateItemDisplayToClientBound("§a"+ability.getSkillName()+" 사용 가능");
                }
                catch(Exception e) {
                    e.printStackTrace();
                }
            }

            if(C - 0.1 < 0) {
                playerCoreData.coolDowns[i] = 0;
            }
            else playerCoreData.coolDowns[i] -= 0.1;
        }
    }

    // 1초마다 한번
    public void updateRegenerations(PlayerCoreData playerCoreData) {
        if(playerCoreData == null) return;
        playerCoreData.addEnergy(playerCoreData.energyRegen);
        playerCoreData.addHealth(playerCoreData.healthRegen);
    }
}
