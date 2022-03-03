package Items.Weapons;

import org.bukkit.inventory.ItemStack;

public enum EnumWeapon {

    FrostWand("FrostWand");

    private final String weaponName;

    EnumWeapon(String weaponName) {
        this.weaponName = weaponName;
    }

    public ItemStack getItem() {
        try {
            Class clazz = Class.forName(weaponName);
            Weapons object = (Weapons) clazz.getDeclaredConstructor(clazz).newInstance();
            ItemStack itemStack = object.getItem();
            return itemStack;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
