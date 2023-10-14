package fleowo.main.event;

import fleowo.main.gui.MemberInventory;
import fleowo.main.gui.PartyInventory;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class InventoryItemAction {
    private ItemStack inventoryStack;

    private Player player;

    public static void setAction(ItemStack stack, String action) {
        NamespacedKey key = NamespacedKey.minecraft("party.action");
        ItemMeta meta = stack.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, action);
        stack.setItemMeta(meta);
    }

    public InventoryItemAction(ItemStack inventoryStack, Player player) {
        this.inventoryStack = inventoryStack;
        this.player = player;
    }

    private String getActionString() {
        NamespacedKey key = NamespacedKey.minecraft("party.action");
        if (this.inventoryStack.getItemMeta().getPersistentDataContainer().has(key, PersistentDataType.STRING))
            return this.inventoryStack.getItemMeta().getPersistentDataContainer().get(key, PersistentDataType.STRING);
        return null;
    }

    public void process() {
        String action = getActionString();
        if (action == null)
            return;
        this.player.closeInventory();
        if (action.equals("party.inventory")) {
            PartyInventory.giveInventory(this.player);
        } else if (action.startsWith("member.inventory")) {
            String memberName = action.split("\\.")[2];
            OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(memberName);
            MemberInventory.giveInventory(this.player, offlinePlayer);
        } else if (action.equals("party.delete")) {
            PlayerCommandPreprocessEvent event = new PlayerCommandPreprocessEvent(this.player, "/party leave");
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (!event.isCancelled())
                this.player.performCommand("party leave");
        } else if (action.startsWith("party.kick")) {
            String memberName = action.split("\\.")[2];
            PlayerCommandPreprocessEvent event = new PlayerCommandPreprocessEvent(this.player, "/party kick " + memberName);
            Bukkit.getServer().getPluginManager().callEvent((Event)event);
            if (!event.isCancelled())
                this.player.performCommand("party kick " + memberName);
        } else if (action.startsWith("party.owner")) {
            String memberName = action.split("\\.")[2];
            this.player.performCommand("party setowner " + memberName);
        }
    }
}
