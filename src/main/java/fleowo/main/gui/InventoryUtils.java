package fleowo.main.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import fleowo.main.event.InventoryItemAction;
import fleowo.main.mechanics.party_value.PartyMemberValueManager;
import fleowo.main.mechanics.party_value.PartyValue;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.persistence.PersistentDataType;

public class InventoryUtils {
    protected static void addPlayerHead(Inventory inventory, OfflinePlayer player, String role, int slot, String action) {
        ItemStack skullOwner = createSkullItem(player);
        ItemMeta meta = skullOwner.getItemMeta();

        String playerName = player.isOnline() ? (((Player) player).getDisplayName()) : (ChatColor.GRAY + player.getName());
        meta.setDisplayName(playerName);
        String status = player.isOnline() ? (ChatColor.GREEN + "ONLINE") : (ChatColor.RED + "OFFLINE");
        List<PartyValue> toShow = PartyMemberValueManager.getInstance().getValues().values().stream().filter(f -> f.isVisible()).collect(Collectors.toList());
        List<String> list = new ArrayList<>(Arrays.asList(ChatColor.GRAY + "Role : " + ChatColor.BLUE + role, ChatColor.GRAY + "Status : " + status));
        List<String> list2 = new ArrayList<>();
        for (PartyValue value : toShow)
            list2.add(value.getText());
        list.addAll(list2);
        meta.setLore(list);
        skullOwner.setItemMeta(meta);
        if (action != null)
            InventoryItemAction.setAction(skullOwner, action);
        inventory.setItem(slot, skullOwner);
    }

    protected static ItemStack createSkullItem(OfflinePlayer player) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta meta = (SkullMeta)item.getItemMeta();
        meta.setOwningPlayer(Bukkit.getOfflinePlayer(player.getUniqueId()));
        item.setItemMeta(meta);
        return item;
    }

    protected static void addPlayerHead(Inventory inventory, OfflinePlayer player, String role, int slot) {
        addPlayerHead(inventory, player, role, slot, null);
    }

    protected static void addItem(Inventory inventory, Material material, String name, List<String> lores, int slot, String action) {
        ItemStack stack = new ItemStack(material);
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lores);
        stack.setItemMeta(meta);
        if (action != null)
            InventoryItemAction.setAction(stack, action);
        inventory.setItem(slot, stack);
    }

    protected static void addItem(Inventory inventory, ItemStack stack, String name, List<String> lores, int slot, String action) {
        ItemMeta meta = stack.getItemMeta();
        meta.setDisplayName(name);
        meta.setLore(lores);
        stack.setItemMeta(meta);
        if (action != null)
            InventoryItemAction.setAction(stack, action);
        inventory.setItem(slot, stack);
    }

    protected static void fillInventory(Inventory inventory, Material material) {
        for (int i = 0; i < inventory.getSize(); i++) {
            ItemStack stack = new ItemStack(material);
            ItemMeta meta = stack.getItemMeta();
            meta.setDisplayName(" ");
            stack.setItemMeta(meta);
            inventory.setItem(i, stack);
        }
    }

    protected static void setPartyInventoryItem(ItemStack stack) {
        NamespacedKey key = NamespacedKey.minecraft("party_inventory");
        ItemMeta meta = stack.getItemMeta();
        meta.getPersistentDataContainer().set(key, PersistentDataType.BYTE, Byte.valueOf((byte)1));
        stack.setItemMeta(meta);
    }
}
