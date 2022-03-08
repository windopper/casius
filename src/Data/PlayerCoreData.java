package Data;

import Ability.Ability;
import Interacts.BuffContainer;
import Interacts.DeBuffContainer;
import Items.Armors.ArmorType;
import Items.Armors.Armors;
import Items.Weapons.Weapons;
import KeyBinds.Keys;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PlayerCoreData extends CoreData<Player> {

    public int gold = 0;

    /** EQUIPMENTS */
    public Ability[] abilities = new Ability[4];
    public double[] coolDowns = {0, 0, 0, 0};

    public Armors[] armors = new Armors[4];
    public Weapons weapon;
    //TODO 기본 무기 만들기

    public List<Keys> keys = new ArrayList<>();
    public int keyWait = 0;
    public BukkitTask keyDisplayScheduler = null;


    /** CONFIGURATIONS */
    public boolean onDeath = false;
    public boolean showParticle = true;
    public Set<Player> nearbyRainStorms = new HashSet<>();

    PlayerCoreData(Player master) {
        super(master);
    }

    public void setArmor(Armors armor) {
        this.armors[armor.armorType.ordinal()] = armor;
    }

    public void removeArmor(ArmorType armorType) {
        this.armors[armorType.ordinal()] = null;
    }

    public void setWeapon(Weapons weapon) {

        this.weapon = weapon;
        UpdateCoreData.applyCurrentItemData(this);
    }

    public void setAbility(Ability ability) {
        Bukkit.broadcastMessage(String.valueOf(ability.abilitySlot.ordinal()));
        this.abilities[ability.abilitySlot.ordinal()] = ability;
    }

    public void addHealth(int var) {
        if(currentHealth + var > health) {
            currentHealth = health;
            return;
        }
        currentHealth += var;
    }

    public void addEnergy(int var) {
        if(currentEnergy + var > energy) {
            currentEnergy = energy;
            return;
        }
        currentEnergy += var;
    }

    public void removeGold(int value) throws IllegalArgumentException {
        if(gold - value < 0) throw new IllegalArgumentException();
    }
}
