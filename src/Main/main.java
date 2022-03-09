package Main;

import Commands.GameCommands;
import Commands.ItemCommands;
import Commands.SkillCommands;
import EventListener.CoreListener;
import EventListener.ProjectileListener;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(new CoreListener() ,this);
        Bukkit.getPluginManager().registerEvents(new ProjectileListener(), this);
//        Bukkit.getPluginManager().registerEvents(new PacketListener(), this);

        init();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public void init() {
        Scheduler.CoreScheduler.startScheduler();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if(!player.isOp()) return true;

        String cmdName = command.getName().toLowerCase();

        ItemCommands.Listen(player, cmdName, args);
        SkillCommands.Listen(player, cmdName, args);
        GameCommands.Listen(player, cmdName, args);


        return true;
    }
}
