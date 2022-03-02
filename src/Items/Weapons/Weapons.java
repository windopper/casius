package Items.Weapons;

import Items.Items;
import Utils.ItemBuilder;
import org.bukkit.inventory.ItemStack;

public abstract class Weapons extends Items {

    public int minElecdmg = 0;
    public int maxElecdmg = 0;
    public int minIcedmg = 0;
    public int maxIcedmg = 0;
    public int minWinddmg = 0;
    public int maxWinddmg = 0;

    protected Weapons(ItemStack itemStack, String Name) {
        super(itemStack, Name);
    }

    public ItemStack getItem() {
        ItemBuilder itemBuilder = ItemBuilder.getBuilder()
                .setItemType(itemStack)
                .setRangeIceDamage(minIcedmg, maxIcedmg)
                .setRangeElecDamage(minElecdmg, maxElecdmg)
                .setRangeWindDamage(minWinddmg, maxWinddmg)
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
