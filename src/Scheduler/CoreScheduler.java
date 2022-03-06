package Scheduler;

import Data.Core;
import Data.PlayerCoreData;
import Main.main;
import org.bukkit.Bukkit;

public class CoreScheduler {
    public static void startScheduler() {

        Bukkit.getScheduler().runTaskTimer(main.getPlugin(main.class), () -> {

            for(PlayerCoreData playerCoreData : Core.getPlayerDatas()) {
                ActionBar.ShowActionBar(playerCoreData);
                RealTimeLoop.getInstance().updateDisplayHealth(playerCoreData);
                RealTimeLoop.getInstance().updateDisplayEnergy(playerCoreData);
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
        }, 0, 20);
    }
}
