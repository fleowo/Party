package fleowo.main.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MemberInventory {
    public static void giveInventory(Player player, OfflinePlayer member) {
        Inventory inventory = Bukkit.createInventory(player, 45, "Party member: " + member.getName());
        InventoryUtils.fillInventory(inventory, Material.BLACK_STAINED_GLASS_PANE);
        InventoryUtils.addItem(inventory, Material.ARROW, ChatColor.GREEN + "Back", null, 39, "party.inventory");
        InventoryUtils.addItem(inventory, Material.BARRIER, ChatColor.RED + "Close", null, 40, "party.close");
        InventoryUtils.addPlayerHead(inventory, member, "Member", 13);
        InventoryUtils.addItem(inventory, Material.LECTERN, ChatColor.YELLOW + "Transfer party owner", null, 21, "party.owner." + member.getName());
        InventoryUtils.addItem(inventory, Material.REDSTONE, ChatColor.YELLOW + "Kick from party",null, 23, "party.kick." + member.getName());
        InventoryUtils.setPartyInventoryItem(inventory.getItem(0));
        player.openInventory(inventory);
    }
}
