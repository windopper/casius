package Items.Armors;

import Items.Items;
import org.bukkit.inventory.ItemStack;

public abstract class Armors extends Items {

    ItemStack itemStack;
    public ArmorType armorType;

    public int health = 0;

    String Name = "";

    Armors(ItemStack itemStack, ArmorType armorType, String Name) {
        this.itemStack = itemStack;
        this.armorType = armorType;
        this.Name = Name;
    }

    public ItemStack getItem() {

        //TODO 구현부 작성
        return itemStack;

    }

}
