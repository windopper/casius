package Ability;

import Data.CoreData;
import Data.PlayerCoreData;
import org.bukkit.entity.LivingEntity;

public interface Shootable {
    void projectileEffect(CoreData<? extends LivingEntity> masterCoreData, CoreData<? extends LivingEntity> targetCoreData);
}
