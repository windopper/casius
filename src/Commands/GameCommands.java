package Commands;

import Data.Core;
import Utils.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class GameCommands {
    public static void Listen(Player master, String cmdName, String[] args) {
        if(cmdName.equals("pmg")) {
            if(args.length == 2) {
                if(args[0].equals("demo")) {
                    List<String> list = new ArrayList<>();
                    list.add("a");
                    list.add("b");
                    list.add("c");
                    list.add("d");
                    ScoreboardUtils.showScoreboardToClientSide(master, args[1], list);
                    Bukkit.broadcastMessage("demo");
                }
            }
            else if(args.length == 1) {
                if(args[0].equals("mission")) {
                    Mission.Mission.startMission(Core.getPlayerData(master));
                }
            }
        }
    }
}
