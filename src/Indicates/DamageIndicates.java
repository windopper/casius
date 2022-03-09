package Indicates;

import Main.main;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;

public class DamageIndicates {

    private final Location location;
    private String content = "";

    private DamageIndicates(Location location) {
        this.location = location;
    }

    public static DamageIndicates getBuilder(Location location) {
        return new DamageIndicates(location);
    }

    public DamageIndicates addContent(String var) {
        content += var;
        return this;
    }

    public DamageIndicates addIceDamage(int damage) {
        content += ChatColor.of("#87ceeb")+"❅ -"+damage+" ";
        return this;
    }

    public DamageIndicates addElecDamage(int damage) {
        content += ChatColor.of("#aa3dff")+"§e⚡ -"+damage+" ";
        return this;
    }

    public DamageIndicates addWindDamage(int damage) {
        content += ChatColor.of("#4e8cf8")+"◎ -"+damage+" ";
        return this;
    }

    public DamageIndicates addEvasion() {
        content += ChatColor.of("#87CEFA")+"회피!";
        return this;
    }

    public void build() {
        if(content.length() == 0) return;

        ArmorStand armorStand = Indicates.getIndicates(location);
        armorStand.setCustomName(content);
        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getPlugin(main.class), armorStand::remove, 25);
    }
}
