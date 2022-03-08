package Ability.Wind;

import Ability.Ability;
import Ability.AbilitySlot;
import Data.CoreData;
import Data.PlayerCoreData;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

public class TrustingWind extends Ability {

    public TrustingWind() {
        super(
                "신뢰의 바람",
                AbilitySlot.RLL,
                12,
                30
        );
    }

    @Override
    public void invokeAbility(PlayerCoreData playerCoreData) {

        int size = playerCoreData.evasions.stream().filter(value -> value.equals("TrustingWind")).toList().size();
        while(size < 3) {
            playerCoreData.evasions.add("TrustingWind");
            size ++;
        }
    }

    @Override
    public void abilityDesign(Location location) {

    }

    @Override
    public void abilityEffect(CoreData<? extends LivingEntity> targetCoreData) {

    }
}
