package fleowo.main.event;

import fleowo.main.gui.PartyInventory;
import fleowo.main.mechanics.PartyManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class InventoryClickEvent implements Listener {
    @EventHandler
    public void onClick(org.bukkit.event.inventory.InventoryClickEvent event) {
        if (!PartyInventory.isPartyInventory(event.getInventory()))
            return;
        event.setCancelled(true);
        if (!(event.getWhoClicked() instanceof Player))
            return;
        Player player = (Player)event.getWhoClicked();
        if (!PartyManager.getInstance().isPartyOwner(player))
            return;
        ItemStack clicked = event.getCurrentItem();
        (new InventoryItemAction(clicked, player)).process();
    }
}
