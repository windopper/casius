package Ability;

import Data.Core;
import Data.CoreData;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AbilityHelper {
    public static List<LivingEntity> getValidEntities(double radius, Location location, LivingEntity master) {

        List<LivingEntity> targetEntities = new ArrayList<>();
        CoreData masterData = Core.getData(master);
        if(masterData == null) return targetEntities;

        for(LivingEntity livingEntity : Objects.requireNonNull(location.getWorld()).getLivingEntities()) {
            Location targetLoc = livingEntity.getLocation();
            if(location.distance(targetLoc) > radius) continue;

            CoreData targetData = Core.getData(livingEntity);
            if(targetData == null) continue;

            targetEntities.add(livingEntity);
        }

        return targetEntities;
    }
}
