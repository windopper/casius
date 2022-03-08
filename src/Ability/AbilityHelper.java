package Ability;

import Data.Core;
import Data.CoreData;
import Data.PlayerCoreData;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;

import java.util.*;

public class AbilityHelper {

    public static List<CoreData<? extends LivingEntity>> getValidEntitiesInRadius(double radius, Location location, Player master) {

        List<CoreData<? extends LivingEntity>> targetCoreDatas = new ArrayList<>();
        PlayerCoreData masterData = Core.getPlayerData(master);
        if(masterData == null) return targetCoreDatas;
        if(masterData.isInvulnerable) return targetCoreDatas;

        for(LivingEntity livingEntity : Objects.requireNonNull(location.getWorld()).getLivingEntities()) {
            Location targetLoc = livingEntity.getEyeLocation();
            if(livingEntity == master) continue;
            if(location.distance(targetLoc) > radius) continue;

            CoreData<? extends LivingEntity> targetData = Core.getData(livingEntity);
            if(targetData == null) continue;
            if(targetData.isInvulnerable) continue;

            targetCoreDatas.add(targetData);
        }

        return targetCoreDatas;
    }

    public static List<CoreData<? extends LivingEntity>> getValidEntitiesInRadiusWithBoundingBox(double radius, Location location, Player master) {

        List<CoreData<? extends LivingEntity>> targetCoreDatas = new ArrayList<>();
        PlayerCoreData masterData = Core.getPlayerData(master);
        if(masterData == null) return targetCoreDatas;
        if(masterData.isInvulnerable) return targetCoreDatas;

        for(LivingEntity livingEntity : Objects.requireNonNull(location.getWorld()).getLivingEntities()) {
            BoundingBox box = livingEntity.getBoundingBox();
            Location targetLoc = livingEntity.getEyeLocation();
            if(livingEntity == master) continue;
            if(location.distance(targetLoc) > radius && !box.contains(location.getX(), location.getY(), location.getZ())) continue;

            CoreData<? extends LivingEntity> targetData = Core.getData(livingEntity);
            if(targetData == null) continue;
            if(targetData.isInvulnerable) continue;

            targetCoreDatas.add(targetData);
        }

        return targetCoreDatas;
    }

    public static Set<PlayerCoreData> getValidPlayersInRadius(double radius, Location location, Player master) {
        Set<PlayerCoreData> targetCoreDatas = new HashSet<>();
        PlayerCoreData masterData = Core.getPlayerData(master);
        if(masterData == null) return targetCoreDatas;
        if(masterData.isInvulnerable) return targetCoreDatas;

        for(PlayerCoreData coreData : Core.getPlayerDatas()) {
            Player target = coreData.master;
            if(target == master) continue;
            if(coreData.isInvulnerable) continue;
            if(location.distance(target.getEyeLocation()) > radius) continue;

            targetCoreDatas.add(coreData);
        }

        return targetCoreDatas;
    }


}
