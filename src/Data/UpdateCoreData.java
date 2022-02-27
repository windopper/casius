package Data;

import org.bukkit.entity.LivingEntity;

import java.util.Set;

public class UpdateCoreData {

    public void updateItem() {

        Set<LivingEntity> entities = Core.getRegisteredEntity();
        for(LivingEntity entity : entities) {

            CoreData coreData = Core.getData(entity);

            int Health = 1000;
            int minIcedmg = 0;
            int maxIcedmg = 0;
            int minElecdmg = 0;
            int maxElecdmg = 0;
            int minWinddmg = 0;
            int maxWinddmg = 0;
            int iceDef = 0;
            int elecDef = 0;
            int windDef = 0;
            int iceDmg = 0;
            int elecDmg = 0;
            int windDmg = 0;
            int walkSpeed = 0;



        }
    }
}
