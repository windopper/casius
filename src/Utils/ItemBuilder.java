package Utils;

import net.minecraft.nbt.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class ItemBuilder implements IItemBuilder {

    public static String OPTION = "OPTION";

    ItemStack itemStack;
    String itemName;
    List<String> Lore;
    int[] tags = new int[14];

    public static ItemBuilder getBuilder() {
        return new ItemBuilder();
    }

    @Override
    public ItemBuilder setItemType(Material material) {
        itemStack = new ItemStack(material, 1);
        return this;
    }

    @Override
    public ItemBuilder setMinIceDamage(int var) {
        tags[0] = var;
        return this;
    }

    @Override
    public ItemBuilder setMaxIceDamage(int var) {
        tags[1] = var;
        return this;
    }

    @Override
    public ItemBuilder setMinElecDamage(int var) {
        tags[2] = var;
        return this;
    }

    @Override
    public ItemBuilder setMaxElecDamage(int var) {
        tags[3] = var;
        return this;
    }

    @Override
    public ItemBuilder setMinWindDamage(int var) {
        tags[4] = var;
        return this;
    }

    @Override
    public ItemBuilder setMaxWinDamage(int var) {
        tags[5] = var;
        return this;
    }

    @Override
    public ItemBuilder setMinIceDefense(int var) {
        tags[6] = var;
        return this;
    }

    @Override
    public ItemBuilder setMaxIceDefense(int var) {
        tags[7] = var;
        return this;
    }

    @Override
    public ItemBuilder setMinElecDefense(int var) {
        tags[8] = var;
        return this;
    }

    @Override
    public ItemBuilder setMaxElecDefense(int var) {
        tags[9] = var;
        return this;
    }

    @Override
    public ItemBuilder setMinWindDefense(int var) {
        tags[10] = var;
        return this;
    }

    @Override
    public ItemBuilder setMaxWindDefense(int var) {
        tags[11] = var;
        return this;
    }

    @Override
    public ItemBuilder setAdditionalHealth(int var) {
        tags[12] = var;
        return this;
    }

    @Override
    public ItemBuilder setWalkSpeed(int var) {
        tags[13] = var;
        return this;
    }

    @Override
    public ItemBuilder setItemName(String var) {
        itemName = var;
        return this;
    }

    @Override
    public ItemBuilder setLore(List<String> var) {
        Lore = var;
        return null;
    }

    @Override
    public ItemStack build() {
        net.minecraft.world.item.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
        NBTTagCompound tagContainer = nmsStack.getOrCreateTag();
        tagContainer.setIntArray(OPTION, tags);
        nmsStack.setTag(tagContainer);
        itemStack = CraftItemStack.asBukkitCopy(nmsStack);
        ItemMeta itemMeta = itemStack.getItemMeta();
        if(itemName != null) itemMeta.setDisplayName(itemName);
        if(!Lore.isEmpty()) itemMeta.setLore(Lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
