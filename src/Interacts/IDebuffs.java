package Interacts;

import org.bukkit.entity.Player;

public interface IDebuffs {

    enum Debuffs {
        SLOWNESS
    }

    void setDebuffs(Player player);
    void removeDebuffs(Player player);
    void removeDebuffs(Player player, Debuffs debuffs);
}
