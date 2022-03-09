package Ability.Ice;

import Ability.Ability;
import Ability.AbilitySlot;
import Ability.AbilityHelper;
import Data.CoreData;
import Data.PlayerCoreData;
import Main.main;
import Utils.ParticleUtils;
import Utils.SoundUtils;
import org.bukkit.*;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class IcyShelter extends Ability {

    final private double radius = 4;
    final private int duration = 100;

    public IcyShelter() {
        super(
                "혹한의 보금자리",
                AbilitySlot.RLL,
                5,
                20
        );
    }

    @Override
    public void invokeAbility(PlayerCoreData playerCoreData) {

        Player master = playerCoreData.master;
        Location loc = master.getEyeLocation();
        Vector dir = loc.getDirection().normalize().multiply(1);

        Item item = (Item) master.getWorld().spawnEntity(loc, EntityType.DROPPED_ITEM);
        item.setPickupDelay(999);
        item.setItemStack(new ItemStack(Material.BLUE_ICE, 1));
        item.setGlowing(true);
        item.setVelocity(dir);

        new BukkitRunnable() {

            int count = 0;

            @Override
            public void run() {
                Location itemLoc = item.getLocation();
                ParticleUtils.showParticle(Particle.FIREWORKS_SPARK, itemLoc, 2, 0, 0, 0, 0.5);

                if(count >= 10) {

                    new BukkitRunnable() {
                        int count = 0;
                        final Location finalLoc = itemLoc;
                        @Override
                        public void run() {
                            if(finalLoc.distance(master.getLocation()) < radius) {
                                playerCoreData.addHealth((int) ((double)playerCoreData.health * 0.05));
                                master.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20, 1));
                            }

                            for(CoreData<? extends LivingEntity> targetCoreData : AbilityHelper.getValidEntitiesInRadius(radius, finalLoc, master)) {
                                targetCoreData.setTempDamageGivenModf("IcyShelter", -20, 100);
                            }

                            if(count >= 9) cancel();
                            count++;
                        }
                    }.runTaskTimer(main.getPlugin(main.class), 0, 10);

                    abilityDesign(itemLoc);
                    item.remove();
                    cancel();
                    return;
                }
                count++;
            }
        }.runTaskTimer(main.getPlugin(main.class), 0, 1);
    }

    @Override
    public void abilityDesign(Location location) {
        SoundUtils.playSound(location, Sound.BLOCK_GLASS_BREAK, 1, 1);
        final List<Location> locationList = new ArrayList<>();
        for(double phi=0; phi<=Math.PI; phi+=Math.PI/15) {
            for(double theta=0; theta<=2*Math.PI; theta+=Math.PI/15) {

                double x = radius*Math.cos(theta)*Math.sin(phi);
                double y = radius*Math.cos(phi);
                double z = radius*Math.sin(theta)*Math.sin(phi);

                location.add(x,y,z);
                locationList.add(location.clone());

                if(location.getBlock().getType() == Material.AIR) {
                    ParticleUtils.showParticle(Particle.BLOCK_CRACK, location, 5 ,0.2, 0.2, 0.2, 0, Material.ICE.createBlockData());
                    location.getBlock().setType(Material.ICE);
                }
                location.subtract(x, y, z);
            }
        }

        Collections.shuffle(locationList);
        ListIterator<Location> iterator = locationList.listIterator();

        new BukkitRunnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++) {
                    if(iterator.hasNext()) {
                        Location loc = iterator.next();
                        if(loc.getBlock().getType() == Material.ICE) {
                            loc.getBlock().setType(Material.AIR);
                        }
                    }
                    else {
                        cancel();
                        return;
                    }
                }
            }
        }.runTaskTimer(main.getPlugin(main.class), duration, 1);
    }

    @Override
    public void abilityEffect(CoreData<? extends LivingEntity> targetCoreData) {

    }
}
