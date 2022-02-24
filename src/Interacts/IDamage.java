package Interacts;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public interface IDamage {
    void invoke(Player master, LivingEntity target, int damage);
}
