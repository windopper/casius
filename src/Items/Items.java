package Items;

import org.bukkit.inventory.ItemStack;

public abstract class Items {

    public ItemStack itemStack;
    public int elecdef = 0;
    public int icedef = 0;
    public int winddef = 0;
    public int elecdmg = 0;
    public int icedmg = 0;
    public int winddmg = 0;
    public int walkSpeed = 0;
    public int price = 0;

    public String Name = "";

    public abstract ItemStack getItem();
}
