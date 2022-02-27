package Items.Armors;

import Items.IPassive;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class FrostArmor extends Armors implements IPassive {


    public FrostArmor() {
        super(
                new ItemStack(Material.DIAMOND_CHESTPLATE, 1),
                ArmorType.CHESTPLATE,
                "FrostArmor"
        );
    }

    @Override
    public void usePassive(Player player) {

    }
}
