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
            Class clazz = Class.forName("Items.Weapons."+name());
            Weapons object = (Weapons) clazz.getDeclaredConstructor().newInstance();
            ItemStack itemStack = object.getItem();
            return itemStack;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Weapons getWeapon() {
        try {
            Class clazz = Class.forName("Items.Weapons."+name());
            Weapons weapon = (Weapons) clazz.getDeclaredConstructor().newInstance();
            return weapon;
        }
        catch(Exception e) {
            return null;
        }
    }

    @Override
    public String getName() {
        return this.weaponName;
    }
}
