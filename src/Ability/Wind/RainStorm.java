package Ability.Wind;

import Ability.Ability;
import Ability.AbilitySlot;
import Data.Core;
import Data.CoreData;
import Data.PlayerCoreData;
import Main.main;
import Utils.ParticleUtils;
import net.minecraft.network.protocol.game.PacketPlayOutGameStateChange;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class RainStorm extends Ability {

    public BukkitTask scheduler;
    private final int stormDistance = 10;
    private final int stormHeight = 7;
    private boolean onStorm = false;

    public RainStorm() {
        super(
                "폭풍우",
                AbilitySlot.RRL,
                5,
                50
        );
    }

    @Override
    public void init(PlayerCoreData playerCoreData) {
        super.init(playerCoreData);
        if(onStorm) manipulatedEnergyRequire = 0;
    }

    @Override
    public void invokeAbility(final PlayerCoreData playerCoreData) {

        Player master = playerCoreData.master;

        onStorm = !onStorm;

        if(scheduler != null) {
            scheduler.cancel();
            scheduler = null;

            PacketPlayOutGameStateChange packet = new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.b, 1);

            for(PlayerCoreData data : Core.getPlayerDatas()) {
                Player player = data.master;
                data.nearbyRainStorms.remove(master);
                if(data.nearbyRainStorms.size() == 0) { ((CraftPlayer) player).getHandle().b.sendPacket(packet); }
            }
            return;
        }


        scheduler = new BukkitRunnable() {

            int count = 0;

            @Override
            public void run() {

                abilityDesign(master.getLocation());

                PacketPlayOutGameStateChange rainStart = new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.c, 1);
                PacketPlayOutGameStateChange rainStop = new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.b, 1);

                for(PlayerCoreData data : Core.getPlayerDatas()) {
                    Player player = data.master;
                    if(player.getLocation().distance(master.getLocation()) < stormDistance) {
                        data.nearbyRainStorms.add(master);
                        ((CraftPlayer) player).getHandle().b.sendPacket(rainStart);
                    }
                    else {
                        data.nearbyRainStorms.remove(master);
                        if(data.nearbyRainStorms.size() == 0) { ((CraftPlayer) player).getHandle().b.sendPacket(rainStop); }
                    }
                }

                if(count == 4) {
                    playerCoreData.addEnergy(-5);
                    count = 0;
                }
                if(playerCoreData.currentEnergy <= 0) {
                    cancel();
                    scheduler = null;
                }
                count++;
            }
        }.runTaskTimer(main.getPlugin(main.class), 0, 5);
    }

    @Override
    public void abilityDesign(Location location) {
        ParticleUtils.showParticle(Particle.CLOUD, location.clone().add(0, stormHeight, 0), 40, 3, 0.2, 3, 0);
        ParticleUtils.showParticle(Particle.REDSTONE, location.clone().add(0, stormHeight, 0), 50, 3, 0.2, 3, 0,
                new Particle.DustOptions(Color.GRAY, 2));
        ParticleUtils.showParticle(Particle.WATER_DROP, location.clone().add(0, 1, 0), 40, 3, 0.5, 3, 0);
    }

    @Override
    public void abilityEffect(CoreData<? extends LivingEntity> targetCoreData) {

    }
}
