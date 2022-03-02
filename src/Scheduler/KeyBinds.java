package Scheduler;

import Data.CoreData;

public class KeyBinds {

    public static void updateKeyBinds(CoreData coreData) {
        if(coreData.keyWait == 0) coreData.keys.clear();
        else if(coreData.keyWait > 0) coreData.keyWait --;
    }
}
