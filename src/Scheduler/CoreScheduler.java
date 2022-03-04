package Scheduler;

import Data.Core;
import Data.CoreData;
import Main.main;
import org.bukkit.Bukkit;

public class CoreScheduler {
    public static void startScheduler() {

        Bukkit.getScheduler().runTaskTimer(main.getPlugin(main.class), () -> {

            ActionBar.ShowActionBar();

            for(CoreData coreData : Core.getDatas()) {
                RealTimeLoop.getInstance().updateDisplayHealth(coreData);
                KeyBinds.updateKeyBinds(coreData);
            }

        }, 0, 1);

        Bukkit.getScheduler().runTaskTimer(main.getPlugin(main.class), () -> {
            for(CoreData coreData : Core.getDatas()) {
                RealTimeLoop.getInstance().updateCoolDown(coreData);
            }

        }, 0, 2);

        Bukkit.getScheduler().runTaskTimer(main.getPlugin(main.class), () -> {
            for(CoreData coreData : Core.getDatas()) {
                RealTimeLoop.getInstance().updateRegenerations(coreData);
            }
        }, 0, 20);
    }
}
