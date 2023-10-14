package fleowo.main.gui;

import java.util.ArrayList;
import java.util.Arrays;

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
        Inventory inventory = Bukkit.createInventory(player, 54, ChatColor.GRAY + "Your party");
        InventoryUtils.fillInventory(inventory, Material.BLACK_STAINED_GLASS_PANE);
        InventoryUtils.addPlayerHead(inventory, party.getOwner().getOfflinePlayer(), "Party owner", 13, null);
        int i;
        for (i = 0; i < party.getMembers().size(); i++) {
            OfflinePlayer member = party.getMembers().get(i).getOfflinePlayer();
            InventoryUtils.addPlayerHead(inventory, member, "Member", 27 + i, "member.inventory." + member.getName());
        }
        for (int j = i; j < 10; j++)
            InventoryUtils.addItem(inventory, Material.GREEN_STAINED_GLASS_PANE, " ", new ArrayList<>(), 27 + j, null);
        InventoryUtils.addItem(inventory, Material.TNT, ChatColor.RED + "Delete party", Arrays.asList(new String[] { ChatColor.GRAY + "Click to delete the party" }), 16, "party.delete");
        InventoryUtils.setPartyInventoryItem(inventory.getItem(0));
        player.openInventory(inventory);
    }

    private static void giveMemberInventory(Player player) {
        Party party = PartyManager.getInstance().getParty(player);
        Inventory inventory = Bukkit.createInventory(player, 54);
        InventoryUtils.fillInventory(inventory, Material.BLACK_STAINED_GLASS_PANE);
        InventoryUtils.addPlayerHead(inventory, party.getOwner().getOfflinePlayer(), "Party owner", 13, null);
        int i;
        for (i = 0; i < party.getMembers().size(); i++) {
            OfflinePlayer member = party.getMembers().get(i).getOfflinePlayer();
            InventoryUtils.addPlayerHead(inventory, member, "Member", 27 + i);
        }
        for (int j = i; j <= 10; j++)
            InventoryUtils.addItem(inventory, Material.GREEN_STAINED_GLASS_PANE, " ", new ArrayList<>(), 27 + j, (String)null);
        InventoryUtils.setPartyInventoryItem(inventory.getItem(0));
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
