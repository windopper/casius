package Data;

import Ability.Ability;
import Interacts.BuffContainer;
import Interacts.DeBuffContainer;
import Items.Armors.ArmorType;
import Items.Armors.Armors;
import Items.Weapons.Weapons;
import KeyBinds.Keys;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CoreData {

    public final LivingEntity master;

    public int health = 1000;
    public int currentHealth = 1000;
    public int healthRegen = 10; // per second
    public int energy = 200;
    public int currentEnergy = 200;
    public int energyRegen = 5; // per second
    public int minIcedmg = 0;
    public int maxIcedmg = 0;
    public int minElecdmg = 0;
    public int maxElecdmg = 0;
    public int minWinddmg = 0;
    public int maxWinddmg = 0;
    public int iceDef = 0;
    public int elecDef = 0;
    public int windDef = 0;
    public int iceDmg = 0;
    public int elecDmg = 0;
    public int windDmg = 0;
    public int walkSpeed = 0;

    public int gold = 0;

    public List<DeBuffContainer> deBuffs = new ArrayList<>();
    public List<BuffContainer> buffs = new ArrayList<>();

    public Ability[] abilities = new Ability[4];
    public double[] coolDowns = {0, 0, 0, 0};

    public Armors[] armors = new Armors[4];
    public Weapons weapon;
    //TODO 기본 무기 만들기

    public List<Keys> keys = new ArrayList<>();
    public int keyWait = 0;

    public boolean pvpMode = true;
    public boolean showParticle = true;

    CoreData(LivingEntity master) {
        this.master = master;
    }

    public double defHelper(int def) {
        double value = 1000;
        if(def <= 1000) {
            value -= def;
            value /= 1000;
        }
        return value;
    }

    public double dmgHelper(int dmg) {
        double value = 1000;
        if(dmg >= -1000) {
            value += dmg;
            value /= 1000;
        }
        return value;
    }

    public void setArmor(Armors armor) {
        this.armors[armor.armorType.ordinal()] = armor;
    }

    public void removeArmor(ArmorType armorType) {
        this.armors[armorType.ordinal()] = null;
    }

    public void setWeapon(Weapons weapon) {
        this.weapon = weapon;
    }

    public void setAbility(Ability ability) { this.abilities[ability.abilitySlot.ordinal()] = ability; }

    public void addHealth(int var) {
        if(currentHealth + var > health) return;
        currentHealth += var;
    }

    public void addEnergy(int var) {
        if(currentEnergy + var > energy) return;
        currentEnergy += var;
    }

    public void removeGold(int value) throws IllegalArgumentException {
        if(gold - value < 0) throw new IllegalArgumentException();
    }
}
