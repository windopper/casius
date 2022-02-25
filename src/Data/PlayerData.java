package Data;

import Interacts.DebuffType;
import org.bukkit.entity.Player;

public class PlayerData {

    final Player player;

    int Health;

    {
        Health = 1000;
    }

    PlayerData(Player player) {
        this.player = player;
    }
}
