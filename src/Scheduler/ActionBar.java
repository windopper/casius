package Scheduler;

import Data.PlayerCoreData;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class ActionBar {

    private final static String BLANK = "                  ";

    public static void ShowActionBar(PlayerCoreData playerCoreData) {
        LivingEntity master = playerCoreData.master;
        if(!(master instanceof Player player)) return;
        //TODO 액션 바 제작
        int maxHealth = playerCoreData.health;
        int currentHealth = playerCoreData.currentHealth;
        int maxEnergy = playerCoreData.energy;
        int currentEnergy = playerCoreData.currentEnergy;

        String ACTIONBAR = "§c§l♥ §r§c"+currentHealth+" / "+maxHealth+BLANK+" §5§l⚡ §r§5"+currentEnergy+" / "+maxEnergy;
        TextComponent textComponent = new TextComponent(ACTIONBAR);
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, textComponent);
    }
}
