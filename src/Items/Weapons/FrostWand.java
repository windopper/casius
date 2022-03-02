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
    }

    @Override
    public void useActive(Player player) {

    }
}
