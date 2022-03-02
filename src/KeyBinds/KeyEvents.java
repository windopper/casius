package KeyBinds;

import Data.Constants;
import Data.CoreData;

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
        Keys first = keysList.get(0);
        Keys second = keysList.get(1);
        Keys third = keysList.get(2);

        if(first == Keys.R && second == Keys.L && third == Keys.R) {
            
        }
        else if(first == Keys.R && second == Keys.R && third == Keys.R) {

        }
        else if(first == Keys.R && second == Keys.L && third == Keys.L) {

        }
        else {

        }
    }

    public void registerKey(Keys keys, CoreData coreData) {
        coreData.keys.add(keys);
        coreData.keyWait = Constants.KEY_WAIT.getValue();
        if(isKeyCombinationFull(coreData)) {
            initializingKeyCombination(coreData);
        }
    }

}
