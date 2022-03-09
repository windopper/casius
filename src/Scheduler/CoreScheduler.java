package Scheduler;

import Data.Core;
import Data.CoreData;
import Data.PlayerCoreData;
import Main.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

public class CoreScheduler {
    public static void startScheduler() {

        Bukkit.getScheduler().runTaskTimer(main.getPlugin(main.class), () -> {

            for(PlayerCoreData playerCoreData : Core.getPlayerDatas()) {
                ActionBar.ShowActionBar(playerCoreData);
                RealTimeLoop.getInstance().updateDisplayHealth(playerCoreData);
                RealTimeLoop.getInstance().updateDisplayEnergy(playerCoreData);
                RealTimeLoop.getInstance().updateWalkSpeed(playerCoreData);
                KeyBinds.updateKeyBinds(playerCoreData);
            }

        }, 0, 1);

        Bukkit.getScheduler().runTaskTimer(main.getPlugin(main.class), () -> {
            for(PlayerCoreData playerCoreData : Core.getPlayerDatas()) {
                RealTimeLoop.getInstance().updateCoolDown(playerCoreData);
            }

        }, 0, 2);

        Bukkit.getScheduler().runTaskTimer(main.getPlugin(main.class), () -> {
            for(PlayerCoreData playerCoreData : Core.getPlayerDatas()) {
                RealTimeLoop.getInstance().updateRegenerations(playerCoreData);
            }
            for(CoreData<? extends LivingEntity> coreData : Core.getDatas()) {
                DataOrganization.organizeTakenDamages(coreData);
            }
        }, 0, 20);
    }
}
