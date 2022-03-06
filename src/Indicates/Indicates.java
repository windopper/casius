package Indicates;

import Utils.NumberUtil;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class Indicates {

    static ArmorStand getIndicates(Location location) {

        double X = location.getX();
        double Y = location.getY();
        double Z = location.getZ();

        Location _location = new Location(
                location.getWorld(),
                NumberUtil.randomDouble(X-1, X+1),
                NumberUtil.randomDouble(Y-0.5, Y+0.5),
                NumberUtil.randomDouble(Z-1, Z+1)
                );

        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(_location, EntityType.ARMOR_STAND);
        armorStand.setMarker(true);
        armorStand.setInvisible(true);
        armorStand.setInvulnerable(true);
        armorStand.setSmall(true);
        armorStand.setCustomNameVisible(true);

        return armorStand;
    }
}
