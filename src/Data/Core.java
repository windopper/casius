package Data;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.TreeMap;

public class Core {
    private final static HashMap<Player, PlayerData> playerDatas = new HashMap<>();

    public static PlayerData registerPlayer(Player player) {
        PlayerData NewPlayerData = new PlayerData(player);
        playerDatas.put(player, NewPlayerData);
        return NewPlayerData;
    }

    public static PlayerData getPlayerData(Player player) {
        return playerDatas.get(player);
    }
}
