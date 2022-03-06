package Data;

import Interacts.BuffContainer;
import Interacts.DeBuffContainer;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class CoreData<T extends LivingEntity> {

    public final T master;

    /** STATS */
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

    /** EFFECTS */
    public List<DeBuffContainer> deBuffs = new ArrayList<>();
    public List<BuffContainer> buffs = new ArrayList<>();
    public ConcurrentLinkedQueue<String> evasions = new ConcurrentLinkedQueue<>();

    public CoreData(T master) {
        this.master = master;
    }

}
