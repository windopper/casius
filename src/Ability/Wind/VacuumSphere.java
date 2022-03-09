package Ability.Wind;

import Ability.Ability;
import Ability.AbilitySlot;
import Ability.AbilityHelper;
import Data.CoreData;
import Data.PlayerCoreData;
import Main.main;
import Utils.DamageUtils;
import Utils.MotionUtils;
import Utils.ParticleUtils;
import Utils.SoundUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.HashSet;
import java.util.Set;

public class VacuumSphere extends Ability {
    public VacuumSphere() {
        super(
                "진공탄",
                AbilitySlot.RLR,
                12,
                30
        );
    }

    @Override
    public void invokeAbility(PlayerCoreData playerCoreData) {
        Set<CoreData<? extends LivingEntity>> hit = new HashSet<>();
        Player master = playerCoreData.master;
        Location loc = master.getEyeLocation();
        Vector v = loc.getDirection().normalize().multiply(0.5);
        SoundUtils.playSound(loc, Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 1);

        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                for(int i=0; i<4; i++) {
                    ParticleUtils.showParticle(Particle.END_ROD, loc, 1, 0, 0, 0, 0);
                    for(CoreData<? extends LivingEntity> targetCoreData : AbilityHelper.getValidEntitiesInRadiusWithBoundingBox(4, loc, master)) {
                        if(hit.contains(targetCoreData)) continue;
                        DamageUtils.giveDamage(playerCoreData, targetCoreData, 0, 0, 2);
                        hit.add(targetCoreData);
                    }
                    loc.add(v);
                    if(count > 48 || hit.size()>=1) {
                        SoundUtils.playSound(loc, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 2, 2);
                        new BukkitRunnable() {
                            int count = 0;
                            @Override
                            public void run() {

                                for(CoreData<? extends LivingEntity> targetCoreData : AbilityHelper.getValidEntitiesInRadiusWithBoundingBox(5, loc, master)) {
                                    LivingEntity target = targetCoreData.master;
                                    Vector dir = loc.toVector().subtract(target.getLocation().toVector()).normalize();
                                    DamageUtils.giveDamage(playerCoreData, targetCoreData, 0, 0, 0.5);
                                    MotionUtils.playMotion(dir, 0.3, targetCoreData);
                                }
                                ParticleUtils.scatterAxisYCircle(Particle.FIREWORKS_SPARK, loc, 1, 10, 0.3d, null);
                                ParticleUtils.scatterAxisZCircle(Particle.FIREWORKS_SPARK, loc, 1, 10, 0.3d, null);
                                ParticleUtils.scatterAxisXCircle(Particle.FIREWORKS_SPARK, loc, 1, 10, 0.3d,  null);

                                if(count>4) cancel();
                                count++;
                            }
                        }.runTaskTimer(main.getPlugin(main.class), 0, 10);
                        cancel();
                        return;
                    }
                    count++;
                }
            }
        }.runTaskTimer(main.getPlugin(main.class), 0, 1);
    }

    @Override
    public void abilityDesign(Location location) {

    }

    @Override
    public void abilityEffect(CoreData<? extends LivingEntity> targetCoreData) {

    }
}
