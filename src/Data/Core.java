package Data;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Core {

    private final static ConcurrentHashMap<LivingEntity, PlayerCoreData> Datas = new ConcurrentHashMap<>();
    private final static ConcurrentHashMap<LivingEntity, ? extends CoreData<? extends LivingEntity>> CoreDatas = new ConcurrentHashMap<>();

    public static PlayerCoreData register(Player master) {
        PlayerCoreData newPlayerCoreData = new PlayerCoreData(master);
        Datas.put(master, newPlayerCoreData);
        return newPlayerCoreData;
    }

    public static PlayerCoreData getPlayerData(Player master) {
        if(hasData(master)) return Datas.get(master);
        return null;
    }
    
    public static CoreData getData(LivingEntity master) {
        if(hasData(master)) return Datas.get(master);
        return null;
    }

    public static Collection<PlayerCoreData> getPlayerDatas() {
        return Datas.values();
    }

    public static boolean hasData(LivingEntity master) {
        return Datas.containsKey(master);
    }

    public static void removeData(LivingEntity master) { Datas.remove(master); }

    public static Set<LivingEntity> getRegisteredEntity() { return Datas.keySet(); }
}
