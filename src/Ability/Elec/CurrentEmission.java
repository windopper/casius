package Ability.Elec;

import Ability.Ability;
import Ability.AbilityHelper;
import Ability.AbilitySlot;
import Data.CoreData;
import Data.PlayerCoreData;
import Main.main;
import Utils.DamageUtil;
import Utils.ParticleUtil;
import Utils.RotateUtil;
import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class CurrentEmission extends Ability {

    private final int range = 10;

    public CurrentEmission() {
        super(
                "전류방출",
                AbilitySlot.RLR,
                5,
                40
        );
    }

    @Override
    public void invokeAbility(PlayerCoreData playerCoreData) {
        Set<CoreData<? extends LivingEntity>> hit = new HashSet<>();
        Player master = playerCoreData.master;

        abilityDesign(master.getLocation());

        Location location = master.getEyeLocation();
        double yaw = master.getLocation().getYaw();
        double pitch = master.getLocation().getPitch();
        double ryaw = Math.toRadians(yaw);
        double rpitch = Math.toRadians(pitch);

        for(Vector vector : getZigZag()) {
            RotateUtil.transform(vector, ryaw, rpitch, 0);
            Location cloneLoc = location.clone().add(vector);
            ParticleUtil.showParticle(Particle.REDSTONE, cloneLoc, 1, 0, 0, 0, 0, new Particle.DustOptions(Color.YELLOW, 1));

            for(CoreData<? extends LivingEntity> targetCoreData : AbilityHelper.getValidEntitiesInRadiusWithBoundingBox(1.5, cloneLoc, master)) {
                if(hit.contains(targetCoreData)) continue;
                for(int i=0; i<3; i++) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(main.getPlugin(main.class), () -> {
                        DamageUtil.giveDamage(playerCoreData, targetCoreData, 0, 0.9, 0);
                    }, i * 3);
                }
                hit.add(targetCoreData);
            }
        }
    }

    @Override
    public void abilityDesign(Location location) {
        location.getWorld().playSound(location, Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 2f, 1.8f);
    }

    @Override
    public void abilityEffect(CoreData<? extends LivingEntity> targetcoreData) {

    }

    private Set<Vector> getZigZag() {
        Set<Vector> vectorSet = new HashSet<>();
        boolean procDir = false;
        double x = 0;
        for(double z = 0; z<=10; z+=0.1) {
            if(Math.random() < 0.1 || Math.abs(x)+0.1 > 0.6) procDir = !procDir;
            if(procDir) x += 0.1;
            else x -= 0.1;

            Vector v = new Vector(x, 0, z);
            vectorSet.add(v);
        }
        return vectorSet;
    }
}
