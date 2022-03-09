package Mission;

import Data.PlayerCoreData;

public enum MissionType {
    KILL_PLAYER("MissionPlayerKill"),
    KILL_ENTITY("MissionEntityKill"),
    TRANSPORT("MissionTransport"),
    FIND("MissionFind"),
    ;

    private String className;

    MissionType(String className) {
        this.className = className;
    }

    public Mission getMission(PlayerCoreData playerCoreData, MissionType missionType) {
        try {
            Class<?> clazz = Class.forName("Mission."+className);
            return (Mission) clazz.getDeclaredConstructor(PlayerCoreData.class, MissionType.class).newInstance(playerCoreData, missionType);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
