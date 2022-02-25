package Interacts;

import org.bukkit.entity.Player;

public interface IDebuffs {

    void setDeBuffs(Player player);
    void removeDeBuffs(Player player);
    void removeDeBuffs(Player player, DebuffType deBuffs);

}
