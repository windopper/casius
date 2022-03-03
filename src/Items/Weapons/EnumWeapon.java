package Items.Weapons;

import Items.IEnumItem;
import org.bukkit.inventory.ItemStack;

public enum EnumWeapon implements IEnumItem {

    FrostWand("FrostWand");

    private final String weaponName;

    EnumWeapon(String weaponName) {
        this.weaponName = weaponName;
    }

    @Override
    public ItemStack getItem() {
        try {
            Class clazz = Class.forName(name());
            Weapons object = (Weapons) clazz.getDeclaredConstructor(clazz).newInstance();
            ItemStack itemStack = object.getItem();
            return itemStack;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public String getName() {
        return this.weaponName;
    }
}
