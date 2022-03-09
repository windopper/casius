package Ability.Wind;

import Ability.Ability;
import Ability.AbilitySlot;
import Data.CoreData;
import Data.PlayerCoreData;
import Main.main;
import Utils.ParticleUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Breeze extends Ability {
    public Breeze() {
        super(
                "산들바람",
                AbilitySlot.RRL,
                3,
                50
        );
    }

    @Override
    public void invokeAbility(PlayerCoreData playerCoreData) {
        Player master = playerCoreData.master;
        playerCoreData.walkSpeed += 50;
        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getPlugin(main.class), () -> playerCoreData.walkSpeed -= 50, 200);

        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                ParticleUtils.showParticle(Particle.CLOUD, master.getLocation(), 7, 0.5, 0.5, 0.5, 0);
                Location loc = master.getLocation().add(0, 1, 0);
                Vector v1 = new Vector(0, 0, 1);
                Vector v2 = new Vector(0, 0, 1);
                v1.rotateAroundY(Math.toRadians(count * 10));
                v2.rotateAroundY(Math.toRadians(count * 10 + 180));
                loc.add(v1);
                ParticleUtils.showParticle(Particle.TOTEM, loc, 1, 0, 0, 0, 0);
                loc.subtract(v1).add(v2);
                ParticleUtils.showParticle(Particle.TOTEM, loc, 1, 0, 0, 0, 0);
                loc.subtract(v2);
                if(count >= 200) cancel();
                count++;
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
