package fleowo.main.gui;

import java.util.ArrayList;

import fleowo.main.mechanics.Party;
import fleowo.main.mechanics.PartyManager;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class PartyInventory {
    public static void giveInventory(Player player) {
        if (!PartyManager.getInstance().hasParty(player)) {
            player.sendMessage(ChatColor.RED + "You don't have any party");
        } else if (PartyManager.getInstance().isPartyOwner(player)) {
            giveOwnerInventory(player);
        } else {
            giveMemberInventory(player);
        }
    }

    private static void giveOwnerInventory(Player player) {
        Party party = PartyManager.getInstance().getParty(player);
        Inventory inventory = Bukkit.createInventory(player, 45, "Your party");
        InventoryUtils.fillInventory(inventory, Material.BLACK_STAINED_GLASS_PANE);
        InventoryUtils.addPlayerHead(inventory, party.getOwner().getOfflinePlayer(), "Party owner", 13, null);
        int i;
        for (i = 0; i < party.getMembers().size(); i++) {
            OfflinePlayer member = party.getMembers().get(i).getOfflinePlayer();
            InventoryUtils.addPlayerHead(inventory, member, "Member", 20 + i, "member.inventory." + member.getName());
        }
        for (int j = i; j < 5; j++)
            InventoryUtils.addItem(inventory, Material.GREEN_STAINED_GLASS_PANE, ChatColor.RED + "Empty Slot", new ArrayList<>(), 20 + j, null);
        InventoryUtils.addItem(inventory, Material.CAULDRON, ChatColor.YELLOW + "Delete party", null, 41, "party.delete");
        InventoryUtils.addItem(inventory, Material.BARRIER, ChatColor.RED + "Close",null, 40, "party.close");
        InventoryUtils.setPartyInventoryItem(inventory.getItem(0));
        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 0.5F ,1F);
        player.openInventory(inventory);
    }

    private static void giveMemberInventory(Player player) {
        Party party = PartyManager.getInstance().getParty(player);
        Inventory inventory = Bukkit.createInventory(player, 45, "Your party");
        InventoryUtils.fillInventory(inventory, Material.BLACK_STAINED_GLASS_PANE);
        InventoryUtils.addPlayerHead(inventory, party.getOwner().getOfflinePlayer(), "Party owner", 13, null);
        int i;
        for (i = 0; i < party.getMembers().size(); i++) {
            OfflinePlayer member = party.getMembers().get(i).getOfflinePlayer();
            InventoryUtils.addPlayerHead(inventory, member, "Member", 20 + i);
        }
        for (int j = i; j <= 4; j++)
            InventoryUtils.addItem(inventory, Material.GREEN_STAINED_GLASS_PANE, ChatColor.RED + "Empty Slot", new ArrayList<>(), 20 + j, null);
        InventoryUtils.addItem(inventory, Material.REDSTONE, ChatColor.YELLOW + "Leave party", null, 41, "party.delete");
        InventoryUtils.addItem(inventory, Material.BARRIER, ChatColor.RED + "Close",null, 40, "party.close");
        InventoryUtils.setPartyInventoryItem(inventory.getItem(0));
        player.playSound(player.getLocation(), Sound.UI_BUTTON_CLICK, 0.5F ,1F);
        player.openInventory(inventory);
    }

    public static boolean isPartyInventory(Inventory inventory) {
        ItemStack stack = inventory.getItem(0);
        if (stack == null)
            return false;
        if (stack.getType() == Material.AIR)
            return false;
        ItemMeta meta = stack.getItemMeta();
        NamespacedKey key2 = NamespacedKey.minecraft("party_inventory");
        return meta.getPersistentDataContainer().has(key2, PersistentDataType.BYTE);
    }
}
