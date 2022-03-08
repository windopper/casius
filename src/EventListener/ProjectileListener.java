package EventListener;

import Data.Core;
import Data.CoreData;
import Data.ProjectileEnums;
import com.google.common.base.Enums;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;

public class ProjectileListener implements Listener {
    @EventHandler
    public void ProjectileHitEvent(ProjectileHitEvent event) {

        Projectile projectile = event.getEntity();
        Entity targetEntity = event.getHitEntity();
        String projectileName = projectile.getCustomName();

        if(targetEntity == null) return;
        if(!(targetEntity instanceof LivingEntity targetLivingEntity)) return;
        if(projectileName == null) return;
        if(!(projectile.getShooter() instanceof Player player)) return;

        CoreData<? extends LivingEntity> targetCoreData = Core.getData(targetLivingEntity);
        CoreData<? extends LivingEntity> masterCoreData = Core.getData(player);

        if(targetCoreData == null || masterCoreData == null) return;

        if(Enums.getIfPresent(ProjectileEnums.class, projectileName).isPresent()) {
            ProjectileEnums.valueOf(projectileName).invokeProjectileHitAbility(masterCoreData, targetCoreData);
        } else {
            return;
        }
    }
}
