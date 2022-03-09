package Interacts;

import Data.CoreData;
import Indicates.DamageIndicates;
import Utils.ParticleUtils;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;

public class Evasions {
    public static boolean isEvadable(CoreData<? extends LivingEntity> CoreData) {

        LivingEntity master = CoreData.master;

        if(CoreData.evasions.size() > 0) {
            CoreData.evasions.remove();

            DamageIndicates.getBuilder(master.getEyeLocation())
                    .addEvasion()
                    .build();

            ParticleUtils.showParticle(Particle.SNOWBALL, master.getLocation().add(0, 1, 0), 10, 0.2, 0.2, 0.2, 0);

            return true;
        }
        return false;
    }
}
