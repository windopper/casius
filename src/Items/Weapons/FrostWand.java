package Items.Weapons;

import Items.IActive;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FrostWand extends Weapons implements IActive {

    protected FrostWand() {
        super(
                new ItemStack(Material.STICK, 1),
                "FrostWand"
        );

        super.minIcedmg = 50;
        super.maxIcedmg = 100;
        super.minElecdmg = 10;
        super.maxElecdmg = 20;
        super.minWinddmg = 20;
        super.maxWinddmg = 40;
        super.additionalEnergy = 5;
    }

    @Override
    public void useActive(Player player) {

    }
}
