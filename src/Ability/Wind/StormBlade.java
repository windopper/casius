package Ability.Wind;

import Ability.Ability;
import Ability.AbilitySlot;
import Ability.Damageable;
import Data.CoreData;
import Data.PlayerCoreData;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class StormBlade extends Ability implements Damageable {

    public StormBlade() {
        super(
                "ํญํ์นผ๋ ",
                AbilitySlot.RLR,
                3,
                10
        );
    }

    @Override
    public void invokeAbility(PlayerCoreData playerCoreData) {

    }

    @Override
    public void abilityDesign(Location location) {

    }

    @Override
    public void abilityEffect(CoreData<? extends LivingEntity> targetCoreData) {

    }

    @Override
    public void abilityDamage(LivingEntity target) {

    }
}
