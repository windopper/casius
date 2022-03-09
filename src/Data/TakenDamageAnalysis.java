package Data;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TakenDamageAnalysis {

    private final List<DamageRecord> recordList;

    public TakenDamageAnalysis(CoreData<? extends LivingEntity> coreData) {
        this.recordList = coreData.takenDamages.stream().toList();
    }

    public TakenDamageAnalysis sortDamageOrder() {
        int size = recordList.size();

        for(int i=size; i>0; i--) {
            for(int j=0; j<i; j++) {
                DamageRecord temp = recordList.get(j);
                int r_prev = recordList.get(j).Damage();
                int r_next = recordList.get(j+1).Damage();

                if(r_prev < r_next) {
                    recordList.set(j, recordList.get(j+1));
                    recordList.set(j+1, temp);
                }
            }
        }
        return this;
    }

    public void sendResult(Player player, long prevMilliSec) {
        long currentMilli = System.currentTimeMillis();

        List<TempDamageRecord> tempDamageRecords = new ArrayList<>();

        player.sendMessage("§6 || 최근 10초간 받은 피해");

        for(DamageRecord damageRecord : recordList) {
            if(currentMilli - damageRecord.time() >= prevMilliSec) continue;

            long diffMilli = currentMilli - damageRecord.time();

//            String milliToSec = String.format("%.1f", (double)diffMilli / 1000);
            String entityName = damageRecord.causeEntityName();
            String elementName = damageRecord.causeElementName();
            int damage = damageRecord.Damage();

            List<TempDamageRecord> tdr = tempDamageRecords.stream().filter(dr -> dr.entityName.equals(entityName)).toList();

            if(tdr.size()>=1) {
                List<TempDamageRecord> _tdr = tempDamageRecords.stream().filter(dr -> dr.elementName.equals(elementName)).toList();
                if(_tdr.size()>=1) {
                    _tdr.get(0).damage += damage;
                }
                else {
                    tempDamageRecords.add(new TempDamageRecord(entityName, elementName, damage));
                }
            }
            else {
                tempDamageRecords.add(new TempDamageRecord(entityName, elementName, damage));
            }

//            player.sendMessage("§6"+milliToSec+"s §7| §e"+damageRecord.causeEntityName()+"'s "+damageRecord.causeElementName()+" §7| §c"+damageRecord.Damage()+" 피해");
        }

        Optional<TempDamageRecord> optional = tempDamageRecords.stream().reduce((a, b) -> a.damage >= b.damage ? a : b);
        int maxDamage = optional.map(damageRecord -> damageRecord.damage).orElse(0);
        for(TempDamageRecord tempDamageRecord : tempDamageRecords) {
            String entityName = tempDamageRecord.entityName;
            String elementName = tempDamageRecord.elementName;
            int damage = tempDamageRecord.damage;
            String damageString = damage == maxDamage ? "§l"+damage : ""+damage;
            player.sendMessage("§7 || §e"+entityName+"'s "+elementName+" §7| §c-"+damageString);
        }

        player.sendMessage("§6 || ----------------------------");

    }

    public List<DamageRecord> build() {
        return recordList;
    }

    private static class TempDamageRecord {
        String entityName;
        String elementName;
        int damage;

        public TempDamageRecord(String entityName, String elementName, int damage) {
            this.entityName = entityName;
            this.elementName = elementName;
            this.damage = damage;
        }

    }

}
