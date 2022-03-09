package Items.Uses;

import Data.PlayerCoreData;
import Main.main;
import Utils.ParticleUtils;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class HealthPotion extends Uses {

    @Override
    protected void mainFunction(PlayerCoreData playerCoreData) {
        Bukkit.getScheduler().runTaskTimer(main.getPlugin(main.class), () -> {

            playerCoreData.addHealth(playerCoreData.health / 5);

            LivingEntity livingEntity = playerCoreData.master;
            Player player = (Player) livingEntity;

            ParticleUtils.showParticle(Particle.HEART, player.getEyeLocation(), 1, 0, 0, 0, 0);

        }, 0, 20);
    }
}
