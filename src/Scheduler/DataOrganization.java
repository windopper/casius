package Scheduler;

import Data.CoreData;
import org.bukkit.entity.LivingEntity;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

public class DataOrganization {
    public static void organizeTakenDamages(CoreData<? extends LivingEntity> coreData) {
        long currentMillis = System.currentTimeMillis();
        coreData.takenDamages = coreData.takenDamages
                .stream()
                .filter(damageRecord -> currentMillis - damageRecord.time() < 30000)
                .collect(Collectors.toCollection(ConcurrentLinkedQueue::new));
    }
}
