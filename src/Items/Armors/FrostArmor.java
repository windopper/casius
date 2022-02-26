package Items.Armors;

import Items.IPassive;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class FrostArmor extends Armors implements IPassive {

    public FrostArmor() {
        itemStack = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
    }

    @Override
    public void usePassive() {

    }
}
