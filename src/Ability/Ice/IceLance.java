package Ability.Ice;

import Ability.Ability;
import Ability.AbilitySlot;
import Ability.Damageable;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class IceLance extends Ability implements Damageable {

    public IceLance() {
        super(AbilitySlot.RLR, 5);
    }

    @Override
    public void invoke(LivingEntity master) {

    }

    @Override
    public void abilityDesign(Location location) {

    }

    @Override
    public void abilityEffect(LivingEntity target) {

    }

    @Override
    public void abilityDamage(LivingEntity target) {

    }
}
