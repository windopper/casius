package Data;

import Interacts.DeBuffContainer;
import Items.Armors.Armors;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class CoreData {

    public final LivingEntity master;

    public int Health = 1000;

    int mindmg = 0;
    int maxdmg = 0;
    public int iceDef = 0;
    public int elecDef = 0;
    public int windDef = 0;
    public int iceDmg = 0;
    public int elecDmg = 0;
    public int windDmg = 0;

    public int walkSpeed = 0;
    public int gold = 0;

    List<DeBuffContainer> deBuffs = new ArrayList<>();

    Armors boots;
    Armors leggings;
    Armors chestplate;
    Armors helmet;

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
}
