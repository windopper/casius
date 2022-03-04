package Commands;

import Items.Armors.EnumArmor;
import Items.Uses.EnumUse;
import Items.Weapons.EnumWeapon;
import com.google.common.base.Enums;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class ItemCommands {
    public static void Listen(Player master, String cmdName, String args[]) {
        if(cmdName.equals("pmg")) {
            if(args.length == 2) {
                if(args[0].equals("getitem")) {
                    String itemName = args[1];
                    ItemStack itemStack = null;
                    if(Enums.getIfPresent(EnumArmor.class, itemName).isPresent()) itemStack = EnumArmor.valueOf(itemName).getItem();
                    else if(Enums.getIfPresent(EnumUse.class, itemName).isPresent()) itemStack = EnumUse.valueOf(itemName).getItem();
                    else if(Enums.getIfPresent(EnumWeapon.class, itemName).isPresent()) itemStack = EnumWeapon.valueOf(itemName).getItem();

                    if(itemStack == null) {
                        master.sendMessage("아이템 "+itemName+" 이 존재하지 않습니다");
                    }
                    else {
                        if(master.getInventory().firstEmpty() == -1) master.sendMessage("인벤토리 공간이 없습니다!");
                        else master.getInventory().addItem(itemStack);
                    }
                }
            }
        }
    }
}
