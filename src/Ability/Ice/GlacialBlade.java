package Ability.Ice;

import Ability.Ability;
import Ability.AbilityHelper;
import Ability.AbilitySlot;
import Data.CoreData;
import Data.PlayerCoreData;
import Utils.DamageUtils;
import Utils.NumberUtils;
import Utils.ParticleUtils;
import Utils.SoundUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Set;

public class GlacialBlade extends Ability {

    private final double distance = 5;

    public GlacialBlade() {
        super(
                "얼음칼날",
                AbilitySlot.RLR,
                10,
                40
        );
    }

    @Override
    public void init(PlayerCoreData playerCoreData) {
        int ice = playerCoreData.iceDmg;
        double coolDec = 0.5 * (int)((double)ice / 100);
        super.init(playerCoreData);
        super.manipulatedCoolDown = super.coolDown - coolDec;
    }

    @Override
    public void invokeAbility(PlayerCoreData playerCoreData) {
        final Set<CoreData<? extends LivingEntity>> hit = new HashSet<>();
        final Player master = playerCoreData.master;
        final Location loc = master.getEyeLocation().add(0, -0.5, 0);

        SoundUtils.playSound(loc, Sound.BLOCK_GLASS_BREAK, 1, 0.5f);
        SoundUtils.playSound(loc, Sound.ENTITY_PLAYER_ATTACK_SWEEP, 1, 1);

        ParticleUtils.bladeShape(Particle.BLOCK_CRACK, loc, loc.getYaw(), loc.getPitch(), NumberUtils.randomDouble(-30, 30), 120, 5,
                2, 6, 0.4, 4, true,
                (l) -> {
                    ParticleUtils.showParticle(Particle.SNOWFLAKE, l, 1, 0, 0, 0, 0);
                    for(CoreData<? extends LivingEntity> targetCoreData : AbilityHelper.getValidEntitiesInRadiusWithBoundingBox(2, l, master)) {
                        if(hit.contains(targetCoreData)) continue;
                        DamageUtils.giveDamage(playerCoreData, targetCoreData, 2, 0, 0, EnumIceAbility.GlacialBlade.name());
                        hit.add(targetCoreData);
                    }
                },
                Material.ICE.createBlockData());
    }

    @Override
    public void abilityDesign(Location location) {

    }

    @Override
    public void abilityEffect(CoreData<? extends LivingEntity> targetCoreData) {

    }
}
