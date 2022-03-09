package Utils;

import org.bukkit.entity.Player;

public class SpeedUtils {

    private static final float defaultWalkSpeed = 0.2f;

    public static void setWalkSpeed(Player player, float walkSpeed) {
        player.setWalkSpeed(walkSpeed);
    }

    public static float transformWalkSpeedToFloat(int walkSpeed) {
        float _walkSpeed = 1;
        _walkSpeed += (float) walkSpeed / 100;
        if(_walkSpeed < 0) {
            _walkSpeed = 0;
        }
        else if(_walkSpeed > 5) {
            _walkSpeed = 5;
        }
        return defaultWalkSpeed * _walkSpeed;
    }
}
