package Items.Uses;

import Data.PlayerCoreData;
import Items.IEnumItem;
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

    public void useAbility(PlayerCoreData playerCoreData) {
        try {
            Class<?> clazz = Class.forName(name());
            Constructor constructor = clazz.getConstructor();
            Method method = clazz.getMethod("mainFunction");
            method.invoke(constructor.newInstance(), playerCoreData);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
