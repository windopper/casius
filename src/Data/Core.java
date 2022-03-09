package Data;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Core {

    private final static ConcurrentHashMap<LivingEntity, PlayerCoreData> playerCoreDatas = new ConcurrentHashMap<>();
    private final static ConcurrentHashMap<LivingEntity, EntityCoreData> entityCoreDatas = new ConcurrentHashMap<>();

    public static PlayerCoreData register(Player master) {
        PlayerCoreData newPlayerCoreData = new PlayerCoreData(master);
        playerCoreDatas.put(master, newPlayerCoreData);
        return newPlayerCoreData;
    }

    public static PlayerCoreData getPlayerData(Player master) {
        if(hasPlayerData(master)) return playerCoreDatas.get(master);
        return null;
    }

    public static EntityCoreData getEntityData(LivingEntity master) {
        if(hasEntityData(master)) return entityCoreDatas.get(master);
        return null;
    }
    
    public static CoreData<? extends LivingEntity> getData(LivingEntity master) {
        if(hasPlayerData(master)) return playerCoreDatas.get(master);
        if(hasEntityData(master)) return entityCoreDatas.get(master);
        return null;
    }

    public static Collection<PlayerCoreData> getPlayerDatas() {
        return playerCoreDatas.values();
    }

    public static Collection<CoreData<? extends LivingEntity>> getDatas() {
        List<PlayerCoreData> l_pcd = playerCoreDatas.values().stream().toList();
        List<EntityCoreData> l_ecd = entityCoreDatas.values().stream().toList();
        List<CoreData<? extends LivingEntity>> l_cd = new ArrayList<>(l_pcd);
        l_cd.addAll(l_ecd);
        return l_cd;
    }

    public static boolean hasPlayerData(LivingEntity master) {
        return playerCoreDatas.containsKey(master);
    }

    public static boolean hasEntityData(LivingEntity master) { return entityCoreDatas.containsKey(master); }

    public static void removeData(LivingEntity master) { playerCoreDatas.remove(master); }

    public static Set<LivingEntity> getRegisteredEntity() {
//        Set<LivingEntity> livingEntitySet = Datas.keySet().stream().map(p -> (LivingEntity) p).collect(Collectors.toSet()).addAll(entityCoreDatas.keySet());
        return playerCoreDatas.keySet();
    }
}
