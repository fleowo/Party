package fleowo.main.commands.list;

import fleowo.main.commands.SinglePartyCommand;
import fleowo.main.mechanics.Party;
import fleowo.main.mechanics.PartyManager;
import fleowo.main.mechanics.PartyMember;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class KickCommand extends SinglePartyCommand {
    public KickCommand() {
        super("/party kick <player>", "kick", 2, "Kick player from your party");
    }

    public void process(String[] args, Player player) {

        if (!PartyManager.getInstance().hasParty(player)) {
            player.sendMessage(ChatColor.RED + "You don't have any party");
            return;
        }

        if (!PartyManager.getInstance().isPartyOwner(player)) {
            player.sendMessage(ChatColor.RED + "You are not the party owner");
            return;
        }

        OfflinePlayer player1 = Bukkit.getOfflinePlayer(args[1]);
        Party player1Party = PartyManager.getInstance().getParty(player1);
        if (player1Party == null) {
            player.sendMessage(ChatColor.RED + "This player is not in your party");
            return;
        }

        if (player1.equals(player)) {
            player.sendMessage(ChatColor.RED + "You cannot kick yourself");
            return;
        }

        PartyMember member = PartyManager.getInstance().getParty(player).getMember(player1);
        PartyManager.getInstance().getParty(player).removePlayer(member);
        player.sendMessage(ChatColor.GREEN + player1.getName() + " has been removed from your party");
        if (player1.isOnline()) {
            ((Player) player1).sendMessage(ChatColor.YELLOW + "You have been kicked from your party");
        }
    }
}
