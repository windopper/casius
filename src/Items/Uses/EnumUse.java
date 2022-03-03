package Items.Uses;

import Items.IEnumItem;
import Items.Items;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public enum EnumUse implements IEnumItem {

    HealthPotion("Health Potion");

    private final String useName;

    EnumUse(String useName) {
        this.useName = useName;
    }

    @Override
    public ItemStack getItem() {
        return null;
    }

    @Override
    public String getName() {
        return useName;
    }

    public Method getUseAbility() {
        try {
            Class<?> clazz = Class.forName(name());
            Constructor constructor = clazz.getConstructor();
            Method method = constructor.newInstance().getClass().getMethod("mainFunction", clazz);
            return method;
        }
        catch(Exception e) {
            return null;
        }
    }
}
