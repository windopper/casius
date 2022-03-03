package Data;

import Items.Armors.Armors;
import Items.Weapons.Weapons;
import org.bukkit.entity.LivingEntity;

import java.util.Set;

public class UpdateCoreData {

    public static void applyCurrentItemData(CoreData coreData) {

        int health = Constants.DEFAULT_HEALTH.getValue();
        int healthRegen = Constants.DEFAULT_HEALTH_REGEN.getValue();
        int energy = Constants.DEFAULT_ENERGY.getValue();
        int energyRegen = Constants.DEFAULT_ENERGY_REGEN.getValue();
        int minIcedmg = 0;
        int maxIcedmg = 0;
        int minElecdmg = 0;
        int maxElecdmg = 0;
        int minWinddmg = 0;
        int maxWinddmg = 0;
        int iceDef = 0;
        int elecDef = 0;
        int windDef = 0;
        int icedmg = 0;
        int elecdmg = 0;
        int winddmg = 0;
        int walkSpeed = 0;

        for(Armors armor : coreData.armors) {
            health += armor.additionalHealth;
            healthRegen += armor.healthRegen;
            energy += armor.additionalEnergy;
            energyRegen += armor.energyRegen;
            iceDef += armor.icedef;
            elecDef += armor.elecdef;
            windDef += armor.winddef;
            icedmg += armor.icedmg;
            elecdmg += armor.elecdmg;
            winddmg += armor.winddmg;
            walkSpeed += armor.walkSpeed;
        }

        Weapons weapon = coreData.weapon;

        health += weapon.additionalHealth;
        healthRegen += weapon.healthRegen;
        energy += weapon.additionalEnergy;
        energyRegen = weapon.energyRegen;
        minIcedmg += weapon.minIcedmg;
        maxIcedmg += weapon.maxIcedmg;
        minElecdmg += weapon.minElecdmg;
        maxElecdmg += weapon.maxElecdmg;
        minWinddmg += weapon.minWinddmg;
        maxWinddmg += weapon.maxWinddmg;
        iceDef += weapon.icedef;
        elecDef += weapon.elecdef;
        windDef += weapon.winddef;
        icedmg += weapon.icedmg;
        elecdmg += weapon.elecdmg;
        winddmg += weapon.winddmg;
        walkSpeed += weapon.walkSpeed;


        coreData.health = health;
        coreData.healthRegen = healthRegen;
        coreData.energy = energy;
        coreData.energyRegen = energyRegen;
        coreData.minIcedmg = minIcedmg;
        coreData.maxIcedmg = maxIcedmg;
        coreData.minElecdmg = minElecdmg;
        coreData.maxElecdmg = maxElecdmg;
        coreData.minWinddmg = minWinddmg;
        coreData.maxWinddmg = maxWinddmg;
        coreData.iceDef = iceDef;
        coreData.elecDef = elecDef;
        coreData.windDef = windDef;
        coreData.iceDmg = icedmg;
        coreData.elecDmg = elecdmg;
        coreData.windDmg = winddmg;
        coreData.walkSpeed = walkSpeed;
    }
}
