package Ability.Ice;

import Ability.Ability;
import Ability.AbilitySlot;
import Ability.Shootable;
import Data.CoreData;
import Data.PlayerCoreData;
import Main.main;
import Utils.DamageUtil;
import Utils.NumberUtil;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class Blizzard extends Ability implements Shootable {
    public Blizzard() {
        super(
                "눈보라",
                AbilitySlot.RLL,
                5,
                30
        );
    }

    @Override
    public void invokeAbility(PlayerCoreData playerCoreData) {
        Player master = playerCoreData.master;
        final double min = 0.6;
        final double max = 1.4;
        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {

                Vector v = master.getLocation().getDirection().clone().normalize().multiply(1.3);
                v.setX(v.getX() * NumberUtil.randomDouble(min, max));
                v.setY(v.getY() * NumberUtil.randomDouble(min, max));
                v.setZ(v.getZ() * NumberUtil.randomDouble(min, max));

                for(int i=0; i<7; i++) {
                    Snowball snowball = (Snowball) master.getWorld().spawnEntity(master.getEyeLocation(), EntityType.SNOWBALL);
                    snowball.setShooter(master);
                    snowball.setVelocity(v);
                    snowball.setCustomName("BLIZZARD");
                    snowball.setCustomNameVisible(false);
                }
                if(count >= 60) cancel();
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

    @Override
    public void projectileEffect(CoreData<? extends LivingEntity> masterCoreData, CoreData<? extends LivingEntity> targetCoreData) {
        LivingEntity master = masterCoreData.master;
        LivingEntity target = targetCoreData.master;

        Vector v = master.getEyeLocation().getDirection().normalize().multiply(0.5);
        target.setVelocity(v);
    }
}
