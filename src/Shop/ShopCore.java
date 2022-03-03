package Shop;

import Items.Weapons.EnumWeapon;
import org.bukkit.inventory.Inventory;

public class ShopCore {
    public static Inventory weaponShop() {
        ShopBuilder shopBuilder = ShopBuilder.getBuilder();
        for(EnumWeapon weapon : EnumWeapon.values()) {
            try {
                shopBuilder.addItem(weapon.getItem());
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        return shopBuilder.build();
    }
}
