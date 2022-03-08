package Ability.Elec;

import Ability.Ability;
import Ability.AbilityHelper;
import Ability.AbilitySlot;
import Data.CoreData;
import Data.PlayerCoreData;
import Utils.ParticleUtil;
import Utils.SoundUtil;
import org.bukkit.*;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class ElectromagneticPulse extends Ability {

    private final double radius = 10;

    public ElectromagneticPulse() {
        super(
                "전자기펄스",
                AbilitySlot.RRL,
                6,
                100
        );
    }

    @Override
    public void invokeAbility(PlayerCoreData playerCoreData) {
        Player master = playerCoreData.master;
        playerCoreData.setTempDamageTakenModf("ElectromagneticPulse", 100, 100);
        abilityDesign(master.getLocation());

        for(PlayerCoreData targetData : AbilityHelper.getValidPlayersInRadius(radius, master.getLocation(), master)) {
            targetData.currentEnergy /= 2;
            for(int i=0; i<4; i++) {
                if(targetData.coolDowns[i] > 5) continue;
                targetData.coolDowns[i] = 5;
            }
        }
    }

    @Override
    public void abilityDesign(Location location) {
        location.add(0, 1, 0);
        SoundUtil.playSound(location, Sound.ENTITY_GENERIC_EXPLODE, 1f, 1.5f);
        ParticleUtil.scatterCircle(Particle.CLOUD, location, 1, 5, 1, null);
        ParticleUtil.staticCircle(Particle.SPELL_WITCH, location, radius, 5, 10, 0.2, 2, 0.2, 0, null);
    }

    @Override
    public void abilityEffect(CoreData<? extends LivingEntity> targetcoreData) {

    }
}
