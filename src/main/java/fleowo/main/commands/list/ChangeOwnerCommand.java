package fleowo.main.commands.list;

import fleowo.main.commands.SinglePartyCommand;
import fleowo.main.custom_event.OwnerSwitchEvent;
import fleowo.main.mechanics.Party;
import fleowo.main.mechanics.PartyManager;
import fleowo.main.mechanics.PartyMember;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class ChangeOwnerCommand extends SinglePartyCommand {
    public ChangeOwnerCommand() {
        super("/party setowner <player>", "setowner", 2, "Define new party owner");
    }

    public void process(String[] args, Player player) {
        Player player1 = Bukkit.getPlayer(args[1]);
        if (!PartyManager.getInstance().hasParty(player)) {
            player.sendMessage(ChatColor.RED + "You don't have any party");
            return;
        }
        if (player1 == null) {
            player.sendMessage(ChatColor.RED + "This player isn't online");
            return;
        }
        if (PartyManager.getInstance().getParty(player1) == null) {
            player.sendMessage(ChatColor.RED + "This player is not in your party");
            return;
        }
        if (!PartyManager.getInstance().isPartyOwner(player)) {
            player.sendMessage(ChatColor.RED + "You are not the party owner");
            return;
        }
        if (player1.equals(player)) {
            player.sendMessage(ChatColor.RED + "You are already the party owner");
            return;
        }
        PartyMember owner = PartyManager.getInstance().getParty(player).getOwner();
        PartyMember member = PartyManager.getInstance().getParty(player).getMember(player1);
        Bukkit.getPluginManager().callEvent(new OwnerSwitchEvent(member, owner));
        PartyManager.getInstance().getParty(player).setNewOwner(member);
        player.sendMessage(ChatColor.GREEN + "The new party owner is " + player1.getName());
        player1.sendMessage(ChatColor.GREEN + "You are the new party owner");
    }
}
