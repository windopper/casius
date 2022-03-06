package Scheduler;

import Data.PlayerCoreData;

public class KeyBinds {

    public static void updateKeyBinds(PlayerCoreData playerCoreData) {
        if(playerCoreData.keyWait == 0) playerCoreData.keys.clear();
        else if(playerCoreData.keyWait > 0) playerCoreData.keyWait--;
    }
}
