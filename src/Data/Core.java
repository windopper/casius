package Data;

import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Core {

    private final static ConcurrentHashMap<LivingEntity, CoreData> Datas = new ConcurrentHashMap<>();

    public static CoreData register(LivingEntity master) {
        CoreData newCoreData = new CoreData(master);
        Datas.put(master, newCoreData);
        return newCoreData;
    }

    public static CoreData getData(LivingEntity master) {
        if(hasData(master)) return Datas.get(master);
        return null;
    }

    public static boolean hasData(LivingEntity master) {
        return Datas.containsKey(master);
    }

    public static void removeData(LivingEntity master) { Datas.remove(master); }

    public static Set<LivingEntity> getRegisteredEntity() { return Datas.keySet(); }
}
