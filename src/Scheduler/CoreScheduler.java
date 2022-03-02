package Scheduler;

import Data.Core;
import Data.CoreData;
import Main.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class CoreScheduler {
    public static void startScheduler() {

        Bukkit.getScheduler().runTaskTimer(main.getPlugin(main.class), () -> {
            ActionBar.ShowActionBar();
            RealTimeLoop.getInstance().updateHealth();

            for(CoreData coreData : Core.getDatas()) {
                KeyBinds.updateKeyBinds(coreData);
            }

        }, 0, 1);

        Bukkit.getScheduler().runTaskTimer(main.getPlugin(main.class), () -> {
            RealTimeLoop.getInstance().updateCoolDown();
        }, 0, 2);
    }
}
