package Utils;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemBuilder implements IItemBuilder {

    ItemStack itemStack;
    String itemName;
    List<String> Lore = new ArrayList<>();

    public static ItemBuilder getBuilder() {
        return new ItemBuilder();
    }

    private boolean isZeroValue(int... vars) {
        for(int var : vars) if(var != 0) return false;
        return true;
    }



    @Override
    public ItemBuilder setItemType(Material material) {
        this.itemStack = new ItemStack(material, 1);
        return this;
    }

    @Override
    public ItemBuilder setItemType(ItemStack itemStack) {
        this.itemStack = itemStack;
        return this;
    }

    @Override
    public ItemBuilder enterLine() {
        Lore.add("");
        return this;
    }

    @Override
    public ItemBuilder setRangeIceDamage(int min, int max) {
        if(isZeroValue(min, max)) return this;
        Lore.add(ChatColor.of("#87ceeb")+"❅ 냉기피해량 "+min+" - "+max);
        return this;
    }

    @Override
    public ItemBuilder setRangeElecDamage(int min, int max) {
        if(isZeroValue(min, max)) return this;
        Lore.add(ChatColor.of("#aa3dff")+"⚡ 전격피해량 "+min+" - "+max);
        return this;
    }

    @Override
    public ItemBuilder setRangeWindDamage(int min, int max) {
        if(isZeroValue(min, max)) return this;
        Lore.add(ChatColor.of("#4e8cf8")+"☁ 바람피해량 "+min+" - "+max);
        return this;
    }

    @Override
    public ItemBuilder setIceDefense(int var) {
        if(isZeroValue(var)) return this;
        Lore.add("냉기저항력 "+var);
        return this;
    }

    @Override
    public ItemBuilder setElecDefense(int var) {
        if(isZeroValue(var)) return this;
        Lore.add("전격저항력 "+var);
        return this;
    }

    @Override
    public ItemBuilder setWindDefense(int var) {
        if(isZeroValue(var)) return this;
        Lore.add("바람저항력 "+var);
        return this;
    }

    @Override
    public ItemBuilder setIceDamage(int var) {
        if(isZeroValue(var)) return this;
        Lore.add("냉기증폭량 "+var);
        return this;
    }

    @Override
    public ItemBuilder setElecDamage(int var) {
        if(isZeroValue(var)) return this;
        Lore.add("전격증폭량 "+var);
        return this;
    }

    @Override
    public ItemBuilder setWindDamage(int var) {
        if(isZeroValue(var)) return this;
        Lore.add("바람증폭량 "+var);
        return this;
    }

    @Override
    public ItemBuilder setAdditionalHealth(int var) {
        if(isZeroValue(var)) return this;
        Lore.add("추가체력 "+var);
        return this;
    }

    @Override
    public ItemBuilder setAdditionalEnergy(int var) {
        if(isZeroValue(var)) return this;
        Lore.add("추가에너지 "+var);
        return this;
    }

    @Override
    public ItemBuilder setHealthRegen(int var) {
        if(isZeroValue(var)) return this;
        Lore.add("체력회복력 "+var);
        return this;
    }

    @Override
    public ItemBuilder setEnergyRegen(int var) {
        if(isZeroValue(var)) return this;
        Lore.add("에너지회복력 "+var);
        return this;
    }

    @Override
    public ItemBuilder setWalkSpeed(int var) {
        if(isZeroValue(var)) return this;
        Lore.add("이동속도 "+var);
        return this;
    }

    @Override
    public ItemBuilder setItemName(String var) {
        this.itemName = var;
        return this;
    }

    @Override
    public ItemBuilder setLore(List<String> var) {
        Lore.addAll(var);
        return this;
    }

    @Override
    public ItemStack build() {
        ItemMeta itemMeta = itemStack.getItemMeta();
        assert itemMeta != null;
        itemMeta.setDisplayName(itemName);
        itemMeta.setLore(Lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
