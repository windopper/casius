package Commands;

import Ability.Ability;
import Ability.Wind.EnumWindAbility;
import Data.Core;
import Data.PlayerCoreData;
import com.google.common.base.Enums;
import org.bukkit.entity.Player;

public class SkillCommands {
    public static void Listen(Player master, String cmdName, String args[]) {

        if(cmdName.equals("pmg")) {
            if(args.length == 2) {
                if(args[0].equals("getskill")) {
                    PlayerCoreData playerCoreData = Core.getPlayerData(master);
                    if(playerCoreData == null) return;

                    String skillName = args[1];
                    Ability ability = null;
                    if(Enums.getIfPresent(EnumWindAbility.class, skillName).isPresent()) ability = EnumWindAbility.valueOf(skillName).getSkill();
                    else {
                        master.sendMessage("스킬 "+skillName+" 이 존재하지 않습니다");
                    }

                    if(ability != null) playerCoreData.setAbility(ability);
                }
            }
        }
    }
}
