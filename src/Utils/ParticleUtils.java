package Utils;

import Data.Core;
import Data.PlayerCoreData;
import Main.main;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.craftbukkit.libs.org.eclipse.sisu.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.function.Consumer;

public class ParticleUtils {

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

    public static <T> void scatterAxisYCircle(Particle var1, Location var2, double radius, int stride, double velocity, @Nullable T var6) {
        for(double i = 0; i<360; i += stride) {
            Vector v = new Vector(0, 0, radius);
            v = v.rotateAroundY(i);
            showParticle(var1, var2, 0, v.getX(), v.getY(), v.getZ(), velocity, var6);
        }
    }

    public static <T> void scatterAxisXCircle(Particle var1, Location var2, double radius, int stride, double velocity, @Nullable T var6) {
        for(double i = 0; i<360; i += stride) {
            Vector v = new Vector(0, radius, 0);
            v = v.rotateAroundX(i);
            showParticle(var1, var2, 0, v.getX(), v.getY(), v.getZ(), velocity, var6);
        }
    }

    public static <T> void scatterAxisZCircle(Particle var1, Location var2, double radius, int stride, double velocity, @Nullable T var6) {
        for(double i = 0; i<360; i += stride) {
            Vector v = new Vector(0, radius, 0);
            v = v.rotateAroundZ(i);
            showParticle(var1, var2, 0, v.getX(), v.getY(), v.getZ(), velocity, var6);
        }
    }

    public static <T> void scatterAxisCircle(Particle var1, Location var2, double radius, int stride, double velocity, Vector axis, @Nullable T var6) {
        for(double i = 0; i<360; i += stride) {
            Vector v = new Vector(0, 0, radius);
            v = v.rotateAroundAxis(axis, i);
            showParticle(var1, var2, 0, v.getX(), v.getY(), v.getZ(), velocity, var6);
        }
    }

    public static <T> void convergeAxisCircle(Particle var1, Location var2, double radius, int stride, double velocity, Vector axis, @Nullable T var6) {
        for(double i = 0; i<360; i += stride) {
            Vector v = new Vector(0, 0, radius);
            v = v.rotateAroundAxis(axis, i);
            showParticle(var1, var2.clone().add(v), 0, -v.getX(), -v.getY(), -v.getZ(), velocity, var6);
        }
    }

    public static <T> void staticCircle(Particle var1, Location var2, double radius, int stride, int amount, double delX, double delY, double delZ, double velocity, @Nullable T var3) {
        for(double i =0; i<360; i+= stride) {
            Vector v = new Vector(0, 0, radius);
            v = v.rotateAroundY(i);
            showParticle(var1, var2.clone().add(v), amount, delX, delY, delZ, velocity, var3);
        }
    }

    public static <T> void bladeShape(Particle particle, Location loc, double yaw, double pitch, double roll, int angle,
                                      double angleStride, double minDist, double maxDist, double distStride, int time,
                                      boolean clockwise, @Nullable Consumer<Location> consumer, @Nullable T particleOption) {

        final double schedulerStride = (double)angle / (double)time / angleStride;

        new BukkitRunnable() {

            double rYaw = Math.toRadians(yaw);
            double rPitch = Math.toRadians(pitch);
            double rRoll = Math.toRadians(roll);
            int count = 0;
            double startAngle = clockwise ? -(double)angle / 2 : (double)angle / 2;
            final double addAngle = clockwise ? angleStride : -angleStride;

            @Override
            public void run() {

                for(int i=0; i<schedulerStride; i++) {
                    for(double j = minDist; j<maxDist; j+=distStride) {
                        Vector v = new Vector(0, 0, j);
                        v.rotateAroundY(Math.toRadians(startAngle));
                        RotateUtils.transform(v, rYaw, rPitch, rRoll);
                        loc.add(v);
                        showParticle(particle, loc, 1, 0, 0, 0, 0, particleOption);
                        if(consumer != null) consumer.accept(loc);
                        loc.subtract(v);
                    }
                    startAngle += addAngle;
                }

                if(count >= time) cancel();
                count++;
            }
        }.runTaskTimer(main.getPlugin(main.class), 0, 1);
    }
}
