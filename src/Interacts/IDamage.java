package Interacts;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public interface IDamage {
    void invoke(LivingEntity master, LivingEntity target, int iceDamage, int elecDamage, int windDamage);
}
