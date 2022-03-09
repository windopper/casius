package Interacts;

import Data.Constants;
import Data.PlayerCoreData;
import Data.TakenDamageAnalysis;
import Main.main;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class Death {



    public Death() {

    }

    public void enableDeathState(PlayerCoreData playerCoreData) {
        playerCoreData.onDeath = true;
        playerCoreData.isInvulnerable = true;

        Player master = playerCoreData.master;
        (new TakenDamageAnalysis(playerCoreData)).sendResult(master, 10000);
        master.setGameMode(GameMode.SPECTATOR);

        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {

                if(count == 0) {
                    master.playSound(master.getLocation(), Sound.ENTITY_WITHER_DEATH, 1, 1.5f);
                    master.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 10, 1));
                }

                String respawnRemaining = String.format("%.1f", ((double)Constants.DEFAULT_RESPAWN_TIME.getValue() * 20 - count) / 20);

                master.sendTitle("§c당신은 죽었습니다", respawnRemaining, 0, 10, 10);

                if(count >= Constants.DEFAULT_RESPAWN_TIME.getValue() * 20) {
                    disableDeathState(playerCoreData);
                    cancel();
                }
                count++;
            }
        }.runTaskTimer(main.getPlugin(main.class), 0, 1);
    }

    public void disableDeathState(PlayerCoreData playerCoreData) {
        Player master = playerCoreData.master;
        master.setGameMode(GameMode.ADVENTURE);
        playerCoreData.currentHealth = playerCoreData.health;
        playerCoreData.currentEnergy = playerCoreData.energy;
        playerCoreData.isInvulnerable = false;
        playerCoreData.onDeath = false;
    }


}
