package fleowo.main.commands.list;

import fleowo.main.commands.SinglePartyCommand;
import fleowo.main.custom_event.PartyDeleteEvent;
import fleowo.main.custom_event.PartyLeaveEvent;
import fleowo.main.mechanics.Party;
import fleowo.main.mechanics.PartyManager;
import fleowo.main.mechanics.PartyMember;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class LeaveCommand extends SinglePartyCommand {
    public LeaveCommand() {
        super("/party leave", "leave", 1, "Leave your party");
    }

    public void process(String[] args, Player player) {
        if (!PartyManager.getInstance().hasParty(player)) {
            player.sendMessage(ChatColor.RED + "You don't have any party");
            return;
        }
        Party party = PartyManager.getInstance().getParty(player);
        PartyMember member = new PartyMember(player);
        if (party.getOwner().equals(member)) {
            Bukkit.getPluginManager().callEvent(new PartyDeleteEvent(party));
            PartyManager.getInstance().removeParty(player);
            player.sendMessage(ChatColor.GREEN + "Your party has been removed");
        } else {
            Bukkit.getPluginManager().callEvent(new PartyLeaveEvent(member));
            party.removePlayer(member);
            player.sendMessage(ChatColor.GREEN + "You left your party");
        }
    }
}
