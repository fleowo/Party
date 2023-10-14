package fleowo.main;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;

public class GlobalUtils {
    public static void sendAcceptDenyMessage(Player player) {
        TextComponent acceptMsg = new TextComponent(" [ACCEPT] ");
        acceptMsg.setColor(ChatColor.GREEN);
        acceptMsg.setBold(Boolean.valueOf(true));
        acceptMsg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(
                org.bukkit.ChatColor.GREEN + "Click to accept invitation")).create()));
        acceptMsg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/party accept"));
        TextComponent rejectMsg = new TextComponent(" [DENY] ");
        rejectMsg.setColor(ChatColor.RED);
        rejectMsg.setBold(Boolean.valueOf(true));
        rejectMsg.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (new ComponentBuilder(
                org.bukkit.ChatColor.RED + "Click to deny invitation")).create()));
        rejectMsg.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/party deny"));
        acceptMsg.addExtra(rejectMsg);
        player.spigot().sendMessage(acceptMsg);
    }
}
