package Items.Uses;

import Data.PlayerCoreData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Uses {

    protected abstract void mainFunction(PlayerCoreData playerCoreData);

    protected void use(PlayerCoreData playerCoreData) {
        removeItem((Player) playerCoreData.master);
        mainFunction(playerCoreData);
    }

    private void removeItem(Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        itemStack.setAmount(itemStack.getAmount() - 1);
    }

}
