package Shop;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopBuilder {

    private final Inventory inv;

    public ShopBuilder(Inventory inv) {
        this.inv = inv;
    }

    public static ShopBuilder getBuilder() {
        return new ShopBuilder(Bukkit.createInventory(null, 54, "Shop"));
    }

    public ShopBuilder addItem(ItemStack... itemStacks) {
        inv.addItem(itemStacks);
        return this;
    }

    public Inventory build() { return inv; }
}
