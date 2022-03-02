package Items;

import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public abstract class Items {

    public int elecdef = 0;
    public int icedef = 0;
    public int winddef = 0;
    public int elecdmg = 0;
    public int icedmg = 0;
    public int winddmg = 0;
    public int walkSpeed = 0;
    public int healthRegen = 0;
    public int energyRegen = 0;
    public int additionalEnergy = 0;
    public int additionalHealth = 0;

    public int price = 0;

    public final ItemStack itemStack;
    public final String Name;
    protected List<String> Lore = new ArrayList<>();

    protected Items(ItemStack itemStack, String Name) {
        this.itemStack = itemStack;
        this.Name = Name;
    }

    public abstract ItemStack getItem();
}
