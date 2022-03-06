package Utils;

import Data.Core;
import Data.PlayerCoreData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.libs.org.eclipse.sisu.Nullable;
import org.bukkit.entity.Player;

public class ParticleUtil {

    public static void showParticle(Particle var1, Location var2, int var3, double var4, double var5, double var6, double var7) {
        showParticle(var1, var2, var3, var4, var5, var6, var7, null);
    }

    public static <T> void showParticle(Particle var1, Location var2, int var3, double var4, double var5, double var6, double var7, @Nullable T var8) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            PlayerCoreData playerCoreData = Core.getPlayerData(player);

            if(playerCoreData == null) {
                player.spawnParticle(var1, var2, var3, var4, var5, var6, var7, var8);
                return;
            }

            if (!playerCoreData.showParticle) return;
            player.spawnParticle(var1, var2, var3, var4, var5, var6, var7, var8);
        }
    }
}
