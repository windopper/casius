package Utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import java.util.List;

public interface IItemBuilder {

    ItemBuilder setItemType(Material material);

    ItemBuilder setItemType(ItemStack itemStack);

    ItemBuilder enterLine();

    ItemBuilder setRangeIceDamage(int min, int max);

    ItemBuilder setRangeElecDamage(int min, int max);

    ItemBuilder setRangeWindDamage(int min, int max);

    ItemBuilder setIceDefense(int var);

    ItemBuilder setElecDefense(int var);

    ItemBuilder setWindDefense(int var);

    ItemBuilder setIceDamage(int var);

    ItemBuilder setElecDamage(int var);

    ItemBuilder setWindDamage(int var);

    ItemBuilder setAdditionalHealth(int var);

    ItemBuilder setAdditionalEnergy(int var);

    ItemBuilder setHealthRegen(int var);

    ItemBuilder setEnergyRegen(int var);

    ItemBuilder setWalkSpeed(int var);

    ItemBuilder setItemName(String var);

    ItemBuilder setLore(List<String> var);

    ItemStack build();

}
