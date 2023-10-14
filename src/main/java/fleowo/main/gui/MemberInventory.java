package fleowo.main.gui;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class MemberInventory {
    public static void giveInventory(Player player, OfflinePlayer member) {
        Inventory inventory = Bukkit.createInventory(player, 27, ChatColor.GRAY + "Party member : " + ChatColor.RED + member.getName());
        InventoryUtils.fillInventory(inventory, Material.BLACK_STAINED_GLASS_PANE);
        InventoryUtils.addItem(inventory, Material.ARROW, ChatColor.RED + "Back",
                Arrays.asList(new String[] { ChatColor.GRAY + "Click to make this player the party owner" }), 0, "party.inventory");
        InventoryUtils.addPlayerHead(inventory, member, "Member", 4);
        InventoryUtils.addItem(inventory, Material.LADDER, ChatColor.RED + "Set owner",
                Arrays.asList(new String[] { ChatColor.GRAY + "Click to make this player the party owner" }), 21, "party.owner." + member

                        .getName());
        InventoryUtils.addItem(inventory, Material.MAGMA_BLOCK, ChatColor.RED + "Kick",
                Arrays.asList(ChatColor.GRAY + "Click to remove this player from your party"), 23, "party.kick." + member

                        .getName());
        InventoryUtils.setPartyInventoryItem(inventory.getItem(0));
        player.openInventory(inventory);
    }
}
