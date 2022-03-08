package KeyBinds;

import Data.Constants;
import Data.PlayerCoreData;
import Main.main;
import net.minecraft.network.protocol.game.PacketPlayOutSetSlot;
import net.minecraft.server.network.PlayerConnection;
import org.bukkit.craftbukkit.v1_17_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_17_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;

public class KeyDisplay {

    final private static HashMap<PlayerCoreData, KeyDisplay> instances = new HashMap<>();
    private final PlayerCoreData playerCoreData;
    private final Player player;
    private ItemStack original;
    private int COUNT = 0;

    private KeyDisplay(PlayerCoreData playerCoreData) {
        this.playerCoreData = playerCoreData;
        this.player = playerCoreData.master;
        init();
    }

    public static KeyDisplay getInstance(PlayerCoreData playerCoreData) {
        if(instances.containsKey(playerCoreData)) return instances.get(playerCoreData);
        KeyDisplay instance = new KeyDisplay(playerCoreData);
        instances.put(playerCoreData, instance);
        return instance;
    }

    public void removeInstance() {
        instances.remove(this.playerCoreData);
    }

    private void init() {

        original = player.getInventory().getItemInMainHand();

        new BukkitRunnable() {
            @Override
            public void run() {

                if(COUNT >= Constants.KEY_WAIT.getValue()) {
                    restorationItemInServerBound();
                    removeInstance();
                    cancel();
                    return;
                }
                COUNT++;
            }
        }.runTaskTimer(main.getPlugin(main.class), 0, 1);
    }

    private void restorationItemInServerBound() {
        ListIterator<ItemStack> iterator = player.getInventory().iterator();

        while(iterator.hasNext()) {
            ItemStack targetStack = iterator.next();
            if(targetStack == null) continue;
            if(targetStack.hashCode() != original.hashCode()) continue;

            int targetSlot = iterator.nextIndex() - 1;
            if(targetSlot <= 8) targetSlot += 36;

            PacketPlayOutSetSlot _packet = new PacketPlayOutSetSlot(0, 0, targetSlot, CraftItemStack.asNMSCopy(original));
            final PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().b;
            playerConnection.sendPacket(_packet);
            break;
        }
    }

    private void sendFakeItemToClientBound(int slot, String displayName) {
        ItemStack itemStack = original.clone();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemMeta);
        final PlayerConnection playerConnection = ((CraftPlayer) player).getHandle().b;
        PacketPlayOutSetSlot packet = new PacketPlayOutSetSlot(0, 0, slot + 36, CraftItemStack.asNMSCopy(itemStack));
        playerConnection.sendPacket(packet);
    }

    public void updateItemKeyBindsDisplay() {

        if(!isHeldItemHasItemMeta()) return;

        COUNT = 0;
        restorationItemInServerBound();

        List<Keys> keysList = playerCoreData.keys;
        Keys first = keysList.size() >= 1 ? keysList.get(0) : null;
        Keys second = keysList.size() >= 2 ? keysList.get(1) : null;
        Keys third = keysList.size() >= 3 ? keysList.get(2) : null;

        int slot = player.getInventory().getHeldItemSlot();
        original = player.getInventory().getItemInMainHand();

        String display = "";
        if(first != null) display += "§a§n"+first+"§r§7-";
        else display += "§n §7§r-";

        if(second != null) display += "§a§n"+second+"§r§7-";
        else display += "§n §7§r-";

        if(third != null) display += "§a§n"+third;
        else display += "§n ";

        sendFakeItemToClientBound(slot, display);
    }

    public void updateItemDisplayToClientBound(String displayName) {

        if(!isHeldItemHasItemMeta()) return;

        COUNT = 0;
        restorationItemInServerBound();

        int slot = player.getInventory().getHeldItemSlot();
        original = player.getInventory().getItemInMainHand();

        sendFakeItemToClientBound(slot, displayName);
    }

    private boolean isHeldItemHasItemMeta() {
        return player.getInventory().getItemInMainHand().getItemMeta() != null;
    }
}
