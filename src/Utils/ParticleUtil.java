package Utils;

import Data.Core;
import Data.PlayerCoreData;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.libs.org.eclipse.sisu.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

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

    public static <T> void scatterCircle(Particle var1, Location var2, double radius, int stride, double velocity, @Nullable T var6) {
        for(double i = 0; i<360; i += stride) {
            Vector v = new Vector(0, 0, radius);
            v = v.rotateAroundY(i);
            showParticle(var1, var2, 0, v.getX(), v.getY(), v.getZ(), velocity, var6);
        }
    }

    public static <T> void staticCircle(Particle var1, Location var2, double radius, int stride, int amount, double delX, double delY, double delZ, double velocity, @Nullable T var3) {
        for(double i =0; i<360; i+= stride) {
            Vector v = new Vector(0, 0, radius);
            v = v.rotateAroundY(i);
            showParticle(var1, var2.clone().add(v), amount, delX, delY, delZ, velocity, var3);
        }
    }
}
