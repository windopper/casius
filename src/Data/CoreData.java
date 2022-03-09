package Data;

import Interacts.BuffContainer;
import Interacts.DeBuffContainer;
import Main.main;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
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
    public ConcurrentHashMap<String, Integer> tempDamageGivenModf = new ConcurrentHashMap<>();
    public ConcurrentHashMap<String, Integer> tempDamageTakenModf = new ConcurrentHashMap<>();
    public ConcurrentLinkedQueue<String> evasions = new ConcurrentLinkedQueue<>();

    /** CONFIGURATIONS */
    public boolean isInvulnerable = false; // 피해를 받거나 줄 수 있는 상태. TRUE이면 그 반대
    public boolean unstoppable = false; // 저지불가

    /** RECORDS */
    public ConcurrentLinkedQueue<DamageRecord> takenDamages = new ConcurrentLinkedQueue<>(); // 받았던 피해 저장

    public CoreData(T master) {
        this.master = master;
    }

    public double getRateTempDamageTakenModf() {
        double sum = 100;
        for(int i : tempDamageTakenModf.values()) {
            sum += i;
        }
        double rate = sum / 100;
        if(rate < 0.25) rate = 0.25;

        return rate;
    }

    public double getRateTempDamageGivenModf() {
        double sum = 100;
        for(int i : tempDamageGivenModf.values()) {
            sum += i;
        }
        double rate = sum / 100;
        if(rate < 0.25) rate = 0.25;

        return rate;
    }

    public void setTempDamageTakenModf(String name, int var, int tickLater) {
        if(tempDamageTakenModf.containsKey(name)) return;
        tempDamageTakenModf.put(name, var);
        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getPlugin(main.class), () ->
                tempDamageTakenModf.remove(name), tickLater);
    }

    public void setTempDamageGivenModf(String name, int var, int tickLater) {
        if(tempDamageGivenModf.containsKey(name)) return;
        tempDamageGivenModf.put(name, var);
        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getPlugin(main.class), () ->
                tempDamageGivenModf.remove(name), tickLater);
    }

}
