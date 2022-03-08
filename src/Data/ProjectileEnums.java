package Data;

import Ability.Ability;
import Ability.Shootable;
import org.bukkit.entity.LivingEntity;

public enum ProjectileEnums {
    BLIZZARD("Ability.Ice.Blizzard"),
    ;

    final private String classPath;

    ProjectileEnums(String classPath) {
        this.classPath = classPath;
    }

    public void invokeProjectileHitAbility(CoreData<? extends LivingEntity> masterCoreData, CoreData<? extends LivingEntity> targetCoreData) {
        try {
            Class<?> clazz = Class.forName(classPath);
            Shootable ability = (Shootable) clazz.getDeclaredConstructor().newInstance();
            ability.projectileEffect(masterCoreData, targetCoreData);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
