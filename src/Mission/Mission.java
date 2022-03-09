package Mission;

import Data.PlayerCoreData;
import Main.main;
import Utils.NumberUtils;
import Utils.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public abstract class Mission {

    public final PlayerCoreData playerCoreData;
    public final MissionType missionType;

    public Mission(PlayerCoreData playerCoreData, MissionType missionType) {
        this.missionType = missionType;
        this.playerCoreData = playerCoreData;
    }


    public static void startMission(PlayerCoreData playerCoreData) {
        Player master = playerCoreData.master;
        MissionType missionType = MissionType.values()[NumberUtils.randomInt(0, MissionType.values().length)];
        Mission mission = missionType.getMission(playerCoreData, missionType);
        if(mission == null) {
            master.sendMessage("§c오류 발생.");
            return;
        }
        playerCoreData.privateMission = mission;

        ScoreboardUtils.showScoreboardToClientSide(master, "미션", mission.contentsDetail());
    }

    public abstract void successMission();

    public abstract void updateMission();

    public abstract List<String> contentsDetail();

    protected void finalizeMission() {
        playerCoreData.privateMission = null;
    }
}
