package EventListener;

import Data.Core;
import Data.CoreData;
import Helper.ItemHelper;
import KeyBinds.KeyEvents;
import KeyBinds.Keys;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CoreListener implements Listener {
    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event) {

    }
    @EventHandler
    public void EntityDamageEvent(EntityDamageEvent event) {

    }
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        CoreData coreData = Core.getData(player);
        if(coreData == null) return;

        ItemHelper.ItemType itemType = ItemHelper.classifyItem(event.getItem());

        if(itemType == ItemHelper.ItemType.WEAPON) {
            if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                KeyEvents.getInstance().registerKey(Keys.L, coreData);
            }
            else if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                KeyEvents.getInstance().registerKey(Keys.R, coreData);
            }
        }
        else if(itemType == ItemHelper.ItemType.USES) {

        }


    }
    @EventHandler
    public void PlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent event) {

    }
}
