package Data;

import org.bukkit.entity.LivingEntity;

import java.util.Set;

public class LoopCoreData {
    public void LoopCoreData() {
        Set<LivingEntity> entities = Core.getRegisteredEntity();
        for(LivingEntity entity : entities) {
            CoreData coreData = Core.getData(entity);

        }
    }
}
