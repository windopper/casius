package Scheduler;

import Main.main;
import org.bukkit.Bukkit;

public class CoreScheduler {
    public static void startScheduler() {

        Bukkit.getScheduler().runTaskTimer(main.getPlugin(main.class), () -> {
            ActionBar.ShowActionBar();
            RealTimeLoop.getInstance().updateHealth();
        }, 0, 1);

        Bukkit.getScheduler().runTaskTimer(main.getPlugin(main.class), () -> {
            RealTimeLoop.getInstance().updateCoolDown();
        }, 0, 2);
    }
}
