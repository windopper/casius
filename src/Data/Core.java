package Data;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Core {

    private final static HashMap<LivingEntity, CoreData> Datas = new HashMap<>();

    public static CoreData registerPlayer(LivingEntity master) {
        CoreData newCoreData = new CoreData(master);
        Datas.put(master, newCoreData);
        return newCoreData;
    }

    public static CoreData getData(LivingEntity master) {
        return Datas.get(master);
    }
}
