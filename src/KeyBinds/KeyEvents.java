package KeyBinds;

import Data.Constants;
import Data.CoreData;
import org.bukkit.entity.LivingEntity;

import javax.annotation.Nonnull;
import java.util.List;

public class KeyEvents {

    private static KeyEvents keyEvents;

    private KeyEvents() {

    }

    public static KeyEvents getInstance() {
        if(keyEvents == null) keyEvents = new KeyEvents();
        return keyEvents;
    }

    private boolean isKeyCombinationFull(@Nonnull CoreData coreData) {
        return coreData.keys.size() == 3;
    }

    private void initializingKeyCombination(CoreData coreData) {
        coreData.keys.clear();
    }

    private void runAbility(CoreData coreData) {
        List<Keys> keysList = coreData.keys;
        LivingEntity master = coreData.master;
        Keys first = keysList.get(0);
        Keys second = keysList.get(1);
        Keys third = keysList.get(2);

        if(first == Keys.R && second == Keys.L && third == Keys.R) {
            coreData.abilities[0].run(master);
        }
        else if(first == Keys.R && second == Keys.R && third == Keys.R) {
            coreData.abilities[1].run(master);
        }
        else if(first == Keys.R && second == Keys.L && third == Keys.L) {
            coreData.abilities[2].run(master);
        }
        else {
            coreData.abilities[3].run(master);
        }
    }

    public void registerKey(Keys keys, CoreData coreData) {
        coreData.keys.add(keys);
        coreData.keyWait = Constants.KEY_WAIT.getValue();
        if(isKeyCombinationFull(coreData)) {
            runAbility(coreData);
            initializingKeyCombination(coreData);
        }
    }

}
