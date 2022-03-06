package KeyBinds;

import Ability.Ability;
import Ability.ResultCode;
import Data.Constants;
import Data.PlayerCoreData;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.List;

public class KeyEvents {

    private final PlayerCoreData playerCoreData;
    private final Player player;

    public KeyEvents(PlayerCoreData playerCoreData) {
        this.playerCoreData = playerCoreData;
        this.player = playerCoreData.master;
    }

    private boolean isKeyCombinationFull() {
        return playerCoreData.keys.size() == 3;
    }

    private boolean isFirstKeyEmpty() {
        return playerCoreData.keys.size() == 0;
    }

    private void resetKeyCombination() { playerCoreData.keys.clear(); }

    private void runIfAbilityIsValid(int number) {
        Ability ability = playerCoreData.abilities[number];
        if(ability == null) {
            KeyDisplay.getInstance(playerCoreData).updateItemDisplayToClientBound("§c아직 스킬이 없습니다!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
            return;
        }

        ResultCode resultCode = ability.run(playerCoreData);
        if(resultCode == ResultCode.SUCCESS) {
            KeyDisplay.getInstance(playerCoreData).updateItemDisplayToClientBound("§a"+ability.getSkillName()+" 사용!");
            player.playSound(player.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
        }
        else if(resultCode == ResultCode.ENERGY_SHORTAGE) {
            KeyDisplay.getInstance(playerCoreData).updateItemDisplayToClientBound("§c에너지가 부족합니다!");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
        }
        else if(resultCode == ResultCode.ON_COOLDOWN) {
            KeyDisplay.getInstance(playerCoreData).updateItemDisplayToClientBound("§c"+String.format("%.1f", playerCoreData.coolDowns[number])+"초 남았습니다");
            player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
        }
    }

    private void runAbility() {
        List<Keys> keysList = playerCoreData.keys;
        Keys first = keysList.get(0);
        Keys second = keysList.get(1);
        Keys third = keysList.get(2);

        if(first == Keys.R && second == Keys.L && third == Keys.R) {
            runIfAbilityIsValid(0);
        }
        else if(first == Keys.R && second == Keys.R && third == Keys.R) {
            runIfAbilityIsValid(1);
        }
        else if(first == Keys.R && second == Keys.L && third == Keys.L) {
            runIfAbilityIsValid(2);
        }
        else if(first == Keys.R && second == Keys.R && third == Keys.L){
            runIfAbilityIsValid(3);
        }
    }

    public void registerKey(Keys keys) {

        if(keys == Keys.L && isFirstKeyEmpty()) return;
        playerCoreData.keys.add(keys);

        keySound(keys);
        KeyDisplay.getInstance(playerCoreData).updateItemKeyBindsDisplay();

        playerCoreData.keyWait = Constants.KEY_WAIT.getValue();
        if(isKeyCombinationFull()) {
            runAbility();
            resetKeyCombination();
        }
    }

    private void keySound(Keys keys) {
        if(keys == Keys.R) {
            player.playSound(player.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1f, 2f);
        }
        else {
            player.playSound(player.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 1f, 1.7f);
        }
    }

}
