package Data;

import org.bukkit.entity.LivingEntity;

import java.util.List;

public class analyzeTakenDamages {

    private final List<DamageRecord> recordList;

    public analyzeTakenDamages(CoreData<? extends LivingEntity> coreData) {
        this.recordList = coreData.takenDamages.stream().toList();
    }

    public analyzeTakenDamages sortDamageOrder() {
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

    public List<DamageRecord> build() {
        return recordList;
    }
}
