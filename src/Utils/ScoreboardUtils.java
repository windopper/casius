package Utils;

import net.minecraft.network.chat.IChatBaseComponent;
import net.minecraft.network.protocol.game.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.network.protocol.game.PacketPlayOutScoreboardObjective;
import net.minecraft.network.protocol.game.PacketPlayOutScoreboardScore;
import net.minecraft.server.ScoreboardServer;
import net.minecraft.server.network.PlayerConnection;
import net.minecraft.world.scores.ScoreboardObjective;
import net.minecraft.world.scores.criteria.IScoreboardCriteria;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardUtils {

    public static void showScoreboardToClientSide(Player player, String title, List<String> contents) {
        net.minecraft.world.scores.Scoreboard scoreboard = new net.minecraft.world.scores.Scoreboard();
        ScoreboardObjective obj = scoreboard.registerObjective(title, IScoreboardCriteria.a
                , IChatBaseComponent.ChatSerializer.b(title),
                IScoreboardCriteria.EnumScoreboardHealthDisplay.a);

        obj.a().setDisplaySlot(1, obj);

        PacketPlayOutScoreboardObjective removePacket =
                new PacketPlayOutScoreboardObjective(obj, 1);

        PacketPlayOutScoreboardObjective createPacket =
                new PacketPlayOutScoreboardObjective(obj, 0);

        PacketPlayOutScoreboardObjective updatePacket =
                new PacketPlayOutScoreboardObjective(obj, 2);

        PacketPlayOutScoreboardDisplayObjective display =
                new PacketPlayOutScoreboardDisplayObjective(1, obj);

        PlayerConnection playerConnection = ConnectionUtils.getPlayerConnection(player);

        playerConnection.sendPacket(removePacket);
        playerConnection.sendPacket(createPacket);
        playerConnection.sendPacket(updatePacket);

        for(int i=0; i<contents.size(); i++) {
            playerConnection.sendPacket(new PacketPlayOutScoreboardScore(ScoreboardServer.Action.a, title, contents.get(i), (contents.size())-i-1));
        }
        contents.clear();
        playerConnection.sendPacket(display);
    }

    public static void deleteScoreBoard(Player player) {
        List<String> list = new ArrayList<>();
        showScoreboardToClientSide(player, "X", list);
    }
}
