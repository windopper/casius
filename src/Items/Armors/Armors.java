package Items.Armors;

import org.bukkit.inventory.ItemStack;

public abstract class Armors {

    ItemStack itemStack;
    int health = 0;
    int elecdef = 0;
    int icedef = 0;
    int winddef = 0;
    int elecdmg = 0;
    int icedmg = 0;
    int winddmg = 0;

    String Name = "";

    public ItemStack getItem() {

        //TODO 구현부 작성
        return itemStack;

    }

}
