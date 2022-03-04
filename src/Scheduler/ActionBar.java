package Scheduler;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class ActionBar {
    public static void ShowActionBar() {
        for(Player player : Bukkit.getOnlinePlayers()) {
            //TODO 액션 바 제작
//            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, );
        }
    }
}
