package Indicates;

import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;

public class Indicates {
    static ArmorStand getIndicates(Location location) {
        ArmorStand armorStand = (ArmorStand) location.getWorld().spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setMarker(true);
        armorStand.setVisible(true);
        armorStand.setSmall(true);
        armorStand.setCustomNameVisible(true);

        return armorStand;
    }
}
