package Items.Armors;

import Items.Items;
import Utils.ItemBuilder;
import org.bukkit.inventory.ItemStack;

public abstract class Armors extends Items {

    public final ArmorType armorType;

    Armors(ItemStack itemStack, ArmorType armorType, String Name) {
        super(itemStack, Name);
        this.armorType = armorType;
    }

    public ItemStack getItem() {
        ItemBuilder itemBuilder = ItemBuilder.getBuilder()
                .setItemType(itemStack)
                .setIceDefense(icedef)
                .setElecDefense(elecdef)
                .setWindDefense(winddef)
                .setIceDamage(icedmg)
                .setElecDamage(elecdmg)
                .setWindDamage(winddmg)
                .setWalkSpeed(walkSpeed)
                .setEnergyRegen(energyRegen)
                .setHealthRegen(healthRegen)
                .setAdditionalEnergy(additionalEnergy)
                .setAdditionalHealth(additionalHealth)
                .setItemName(Name)
                .setLore(Lore);

        return itemBuilder.build();

    }

}
