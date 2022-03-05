package Ability.Ice;

import Ability.Ability;
import Ability.AbilitySlot;
import Ability.Damageable;
import Data.CoreData;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class IceLance extends Ability implements Damageable {

    public IceLance() {
        super(AbilitySlot.RLR, 5, 20);
    }

    @Override
    public void invokeAbility(CoreData coreData) {

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
