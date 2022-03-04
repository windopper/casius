package EventListener;

import Data.Core;
import Data.CoreData;
import Helper.ItemHelper;
import KeyBinds.KeyEvents;
import KeyBinds.Keys;
import Utils.DamageUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.*;

public class CoreListener implements Listener {

    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event) {

        if(event.getDamager() instanceof LivingEntity master && event.getEntity() instanceof LivingEntity target) {

            CoreData masterCoreData = Core.getData(master);
            CoreData targetCoreData = Core.getData(target);

            if(masterCoreData == null || targetCoreData == null) return;

            DamageUtil.giveDamage(masterCoreData, targetCoreData, 1);

        }
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
            if(event.getAction() == Action.RIGHT_CLICK_BLOCK || event.getAction() == Action.RIGHT_CLICK_AIR) {

            }
        }
    }

    @EventHandler
    public void PlayerInteractAtEntityEvent(PlayerInteractAtEntityEvent event) {

    }

    @EventHandler
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if(Core.hasData(player)) return;
        Core.register(player);
        Bukkit.broadcastMessage(player.getName()+" is registered in coredata successfully");
    }

    @EventHandler
    public void PlayerQuitEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        if(Core.hasData(player)) Core.removeData(player);
    }
}
