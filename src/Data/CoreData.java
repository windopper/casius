package Data;

import Interacts.DeBuffContainer;
import Items.Armors.ArmorType;
import Items.Armors.Armors;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CoreData {

    public final LivingEntity master;

    public int Health = 1000;

    int minIcedmg = 0;
    int maxIcedmg = 0;
    int minElecdmg = 0;
    int maxElecdmg = 0;
    int minWinddmg = 0;
    int maxWinddmg = 0;
    public int iceDef = 0;
    public int elecDef = 0;
    public int windDef = 0;
    public int iceDmg = 0;
    public int elecDmg = 0;
    public int windDmg = 0;

    public int walkSpeed = 0;
    public int gold = 0;

    public List<DeBuffContainer> deBuffs = new ArrayList<>();

    public Armors[] armors = new Armors[4];

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

    public void setArmor(ArmorType armorType, Armors armor) {
        this.armors[armorType.ordinal()] = armor;
    }

    public void removeGold(int value) throws IllegalArgumentException {
        if(gold - value < 0) throw new IllegalArgumentException();
    }
}
