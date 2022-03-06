package EventListener;

import Data.Core;
import Data.CoreData;
import Data.PlayerCoreData;
import Helper.ItemHelper;
import Interacts.Evasions;
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
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.server.ServerLoadEvent;

public class CoreListener implements Listener {

    @EventHandler
    public void EntityDamageByEntityEvent(EntityDamageByEntityEvent event) {

        if(event.getDamager() instanceof LivingEntity master && event.getEntity() instanceof LivingEntity target) {

            CoreData masterPlayerCoreData = Core.getData(master);
            CoreData targetPlayerCoreData = Core.getData(target);

            if(masterPlayerCoreData == null || targetPlayerCoreData == null) return;

            if(Evasions.isEvadable(targetPlayerCoreData)) {
                event.setCancelled(true);
                return;
            }

            DamageUtil.giveDamage(masterPlayerCoreData, targetPlayerCoreData, 1);

        }
    }

//    @EventHandler
//    public void EntityDamageEvent(EntityDamageEvent event) {
//        if(event.getEntity() instanceof LivingEntity livingEntity) {
//            CoreData masterCoreData = Core.getData(livingEntity);
//            if(masterCoreData == null) return;
////            masterCoreData.currentHealth -= 100;
//        }
//    }

    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        PlayerCoreData playerCoreData = Core.getPlayerData(player);
        if(playerCoreData == null) return;

        ItemHelper.ItemType itemType = ItemHelper.classifyItem(event.getItem());

        if(itemType == ItemHelper.ItemType.WEAPON) {
            if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK) {
                (new KeyEvents(playerCoreData)).registerKey(Keys.L);
            }
            else if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                (new KeyEvents(playerCoreData)).registerKey(Keys.R);
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

    @EventHandler
    public void ServerLoadEvent(ServerLoadEvent event) {
        for(Player player : Bukkit.getOnlinePlayers()) {
            if(Core.hasData(player)) return;
            Core.register(player);
            Bukkit.broadcastMessage(player.getName()+" is registered in coredata successfully");
        }
    }

    @EventHandler
    public void PlayerDeathEvent(PlayerDeathEvent event) {

    }
}
