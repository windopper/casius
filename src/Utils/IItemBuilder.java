package Utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.List;

public interface IItemBuilder {

    ItemBuilder setItemType(Material material);

    ItemBuilder setMinIceDamage(int var);
    ItemBuilder setMaxIceDamage(int var);
    ItemBuilder setMinElecDamage(int var);
    ItemBuilder setMaxElecDamage(int var);
    ItemBuilder setMinWindDamage(int var);
    ItemBuilder setMaxWinDamage(int var);
    ItemBuilder setMinIceDefense(int var);
    ItemBuilder setMaxIceDefense(int var);
    ItemBuilder setMinElecDefense(int var);
    ItemBuilder setMaxElecDefense(int var);
    ItemBuilder setMinWindDefense(int var);
    ItemBuilder setMaxWindDefense(int var);
    ItemBuilder setAdditionalHealth(int var);
    ItemBuilder setWalkSpeed(int var);

    //14

    ItemBuilder setItemName(String var);
    ItemBuilder setLore(List<String> var);
    ItemStack build();

}
