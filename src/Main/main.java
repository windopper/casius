package Main;

import EventListener.CoreListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new CoreListener() ,this);

        init();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public void init() {
        Scheduler.CoreScheduler.startScheduler();
    }
}
