package fleowo.main.commands.list;

import fleowo.main.commands.SinglePartyCommand;
import fleowo.main.mechanics.Party;
import fleowo.main.mechanics.PartyManager;
import fleowo.main.mechanics.PartyMember;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class SayCommand extends SinglePartyCommand {
    public SayCommand() {
        super("/party say <message>", "say", 0, "Talk to your party");
    }

    public void process(String[] args, Player player) {
        if (!PartyManager.getInstance().hasParty(player)) {
            player.sendMessage(ChatColor.RED + "You don't have any party");
            return;
        }
        Party party = PartyManager.getInstance().getParty(player);
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < args.length; i++)
            sb.append(args[i] + " ");
        String playerMessage = sb.toString();
        String message = ChatColor.LIGHT_PURPLE + "[PARTY] " + ChatColor.RESET + player.getDisplayName() + ChatColor.WHITE + ": " + playerMessage;
        for (PartyMember member : party.getMembers()) {
            if (!member.getOfflinePlayer().isOnline())
                continue;
            Player player1 = (Player)member.getOfflinePlayer();
            player1.sendMessage(message);
        }
        if (party.getOwner().getOfflinePlayer().isOnline()) {
            Player player1 = (Player) party.getOwner().getOfflinePlayer();
            player1.sendMessage(message);
        }
    }

    public boolean verifyArgs(int args) {
        return (args > 1);
    }
}
