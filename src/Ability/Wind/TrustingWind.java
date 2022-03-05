package Ability.Wind;

import Ability.Ability;
import Ability.AbilitySlot;
import Data.CoreData;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class TrustingWind extends Ability {

    public TrustingWind() {
        super(
                AbilitySlot.RLL,
                12,
                30
        );
    }

    @Override
    public void invokeAbility(CoreData coreData) {

        int size = coreData.evasions.stream().filter(value -> value.equals("TrustingWind")).toList().size();
        while(size < 3) {
            coreData.evasions.add("TrustingWind");
            size ++;
        }
    }

    @Override
    public void abilityDesign(Location location) {

    }

    @Override
    public void abilityEffect(LivingEntity target) {

    }
}
