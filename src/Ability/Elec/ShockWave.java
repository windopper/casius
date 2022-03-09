package Ability.Elec;

import Ability.Ability;
import Ability.AbilitySlot;
import Ability.AbilityHelper;
import Data.CoreData;
import Data.PlayerCoreData;
import Main.main;
import Utils.*;
import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class ShockWave extends Ability {
    public ShockWave() {
        super(
                "전격파",
                AbilitySlot.RLR,
                15,
                40
        );
    }

    @Override
    public void invokeAbility(PlayerCoreData playerCoreData) {
        Set<CoreData<? extends LivingEntity>> hit = new HashSet<>();
        final Player master = playerCoreData.master;
        Location loc = master.getLocation().add(0, 1, 0);
        final double rYaw = Math.toRadians(loc.getYaw());
        final double rPitch = Math.toRadians(loc.getPitch());

        new BukkitRunnable() {
            int count = 1;
            @Override
            public void run() {

                Vector v = new Vector(0, 0, count);
                RotateUtils.transform(v, rYaw, rPitch, 0);

                for(int i=-30; i<30; i+=5) {
                    Vector _v = v.clone().rotateAroundY(Math.toRadians(i));
                    loc.add(_v);
                    ParticleUtils.showParticle(Particle.BLOCK_CRACK, loc, 5, 0.2, 0.2, 0.2, 0, Material.END_STONE.createBlockData());
                    ParticleUtils.showParticle(Particle.FIREWORKS_SPARK, loc, 1, 0.2, 0.2, 0.2, 0.2);
                    for(CoreData<? extends LivingEntity> targetCoreData : AbilityHelper.getValidEntitiesInRadiusWithBoundingBox(2, loc, master)) {
                        if(hit.contains(targetCoreData)) continue;

                        abilityEffect(targetCoreData);
                        MotionUtils.playMotion(v, 2, targetCoreData);
                        DamageUtils.giveDamage(playerCoreData, targetCoreData, 0, 3, 0);

                        hit.add(targetCoreData);
                    }
                    loc.subtract(_v);
                }

                if(count >= 12) {
                    cancel();
                }
                SoundUtils.playSound(loc.clone().add(v), Sound.BLOCK_GLASS_BREAK, 1, 0.8f);
                count+=1.5;

            }
        }.runTaskTimer(main.getPlugin(main.class), 0, 1);
    }

    @Override
    public void abilityDesign(Location location) {

    }

    @Override
    public void abilityEffect(CoreData<? extends LivingEntity> targetCoreData) {
        LivingEntity target = targetCoreData.master;
        SoundUtils.playSound(target.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
        ParticleUtils.showParticle(Particle.EXPLOSION_HUGE, target.getLocation(), 1, 0.1, 0.1, 0.1, 0);
    }
}
