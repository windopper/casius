package Interacts;

import org.bukkit.entity.Player;

public interface IBuffs {

    void setDeBuffs(Player player);
    void removeDeBuffs(Player player);
    void removeDeBuffs(Player player, BuffType deBuffs);

}
