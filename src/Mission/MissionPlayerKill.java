package Mission;

import Data.PlayerCoreData;
import Main.main;
import Utils.ScoreboardUtils;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class MissionPlayerKill extends Mission {

    private int currentScore = 0;
    private int targetScore = 2;

    public MissionPlayerKill(PlayerCoreData playerCoreData, MissionType missionType) {
        super(playerCoreData, missionType);
    }

    @Override
    public void successMission() {
        Player master = playerCoreData.master;
        List<String> contents = new ArrayList<>();
        contents.add("§b미션 성공!");
        master.playSound(master.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 2);
        ScoreboardUtils.showScoreboardToClientSide(master, "미션", contents);
        Bukkit.getScheduler().scheduleSyncDelayedTask(main.getPlugin(main.class), () -> ScoreboardUtils.deleteScoreBoard(master), 60);

        finalizeMission();
    }

    @Override
    public void updateMission() {
        currentScore ++;
        ScoreboardUtils.showScoreboardToClientSide(super.playerCoreData.master, "미션", contentsDetail());
        if(currentScore >= targetScore) successMission();
    }

    @Override
    public List<String> contentsDetail() {
        List<String> contents = new ArrayList<>();
        contents.add("§7목표 : 플레이어를 제거하세요 §e("+currentScore+"/"+targetScore+")");
        contents.add("");
        contents.add("§b보상 : 짱짱 많은 골드");
        return contents;
    }
}
