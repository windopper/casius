package Ability.Wind;

import Ability.Ability;
import Ability.AbilitySlot;
import Ability.Damageable;
import Data.CoreData;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class StormBlade extends Ability implements Damageable {

    public StormBlade() {
        super(
                AbilitySlot.RLR,
                3,
                10
        );
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