package Items.Uses;

import Data.CoreData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public abstract class Uses {

    protected abstract void mainFunction(CoreData coreData);

    protected void use(CoreData coreData) {
        removeItem((Player) coreData.master);
        mainFunction(coreData);
    }

    private void removeItem(Player player) {
        ItemStack itemStack = player.getInventory().getItemInMainHand();
        itemStack.setAmount(itemStack.getAmount() - 1);
    }

}
