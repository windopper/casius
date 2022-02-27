package Data;

import org.bukkit.entity.LivingEntity;

import java.util.HashMap;
import java.util.Set;

public class Core {

    private final static HashMap<LivingEntity, CoreData> Datas = new HashMap<>();

    public static CoreData register(LivingEntity master) {
        CoreData newCoreData = new CoreData(master);
        Datas.put(master, newCoreData);
        return newCoreData;
    }

    public static CoreData getData(LivingEntity master) {
        return Datas.get(master);
    }

    public static Set<LivingEntity> getRegisteredEntity() { return Datas.keySet(); }
}
