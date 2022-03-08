package Utils;

import org.bukkit.Location;
import org.bukkit.Sound;

public class SoundUtil {
    public static void warnSound() {}
    public static void playSound(Location location, Sound sound, float volume, float pitch) {
        if(location.getWorld() == null) return;
        location.getWorld().playSound(location, sound, volume, pitch);
    }
}
