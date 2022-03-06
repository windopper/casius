package Ability;

import Data.Core;
import Data.PlayerCoreData;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AbilityHelper {

//    public static List<LivingEntity> getValidEntitiesInRadius(double radius, Location location, Player master) {
//
//        List<LivingEntity> targetEntities = new ArrayList<>();
//        PlayerCoreData masterData = Core.getPlayerData(master);
//        if(masterData == null) return targetEntities;
//        if(!masterData.pvpMode) return targetEntities;
//
//        for(LivingEntity livingEntity : Objects.requireNonNull(location.getWorld()).getLivingEntities()) {
//            Location targetLoc = livingEntity.getLocation();
//            if(location.distance(targetLoc) > radius) continue;
//
//            CoreData targetData = Core.getPlayerData(livingEntity);
//            if(targetData == null) continue;
//            if(!targetData.pvpMode) continue;
//
//            targetEntities.add(livingEntity);
//        }
//
//        return targetEntities;
//    }
}
