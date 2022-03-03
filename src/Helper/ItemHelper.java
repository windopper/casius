package Helper;

import Items.Uses.EnumUse;
import Items.Weapons.EnumWeapon;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Objects;

public class ItemHelper {

    public enum ItemType {
        WEAPON,
        USES,
        OTHER
    }

    public static ItemType classifyItem(ItemStack itemStack) {
        try {
            String name = Objects.requireNonNull(itemStack.getItemMeta()).getDisplayName();
            if(Arrays.stream(EnumWeapon.values()).anyMatch(e -> e.getName().equals(name))) return ItemType.WEAPON;
            else if(Arrays.stream(EnumUse.values()).anyMatch(e -> e.getName().equals(name))) return ItemType.USES;
        }
        catch(Exception e) {
            return ItemType.OTHER;
        }
        return ItemType.OTHER;
    }
}
