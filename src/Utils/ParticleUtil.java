package Utils;

import Data.Core;
import Data.CoreData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.entity.Player;

public class ParticleUtil {

    public static void showParticle(Particle var1, Location var2, int var3, double var4, double var5, double var6, double var7) {
        for (Player player : Bukkit.getOnlinePlayers()) {

            CoreData coreData = Core.getData(player);

            if(coreData == null) {
                player.spawnParticle(var1, var2, var3, var4, var5, var6, var7);
                return;
            }

            if (!coreData.showParticle) return;
            player.spawnParticle(var1, var2, var3, var4, var5, var6, var7);

        }
    }
}
