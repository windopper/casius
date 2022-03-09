package Ability.Elec;

import Ability.Ability;
import Ability.AbilitySlot;
import Data.CoreData;
import Data.PlayerCoreData;
import Utils.ParticleUtils;
import Utils.SoundUtils;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Flash extends Ability {

    private final double distance = 7;

    public Flash() {
        super(
                "전광석화",
                AbilitySlot.RRR,
                2,
                20
        );
    }

    @Override
    public void invokeAbility(PlayerCoreData playerCoreData) {
        Player master = playerCoreData.master;
        Location loc = master.getEyeLocation();
        SoundUtils.playSound(loc, Sound.ENTITY_WITHER_SHOOT, 1, 2);
        Vector v = loc.getDirection().normalize().multiply(0.2);
        for(int i=0; i<5*distance; i++) {
            if(!loc.getBlock().isPassable()) {
                loc.subtract(v);
                master.teleport(loc);
                return;
            } else if(i == 5*distance-1) {
                master.teleport(loc);
            }
            ParticleUtils.showParticle(Particle.FIREWORKS_SPARK, loc, 1, 0, 0, 0, 0);
            loc.add(v);
        }
    }

    @Override
    public void abilityDesign(Location location) {

    }

    @Override
    public void abilityEffect(CoreData<? extends LivingEntity> targetCoreData) {

    }
}
