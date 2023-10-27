package fleowo.main.commands.list;

import fleowo.main.commands.SinglePartyCommand;
import fleowo.main.custom_event.PartyJoinEvent;
import fleowo.main.mechanics.PartyManager;
import fleowo.main.mechanics.invitation.InvitationManager;
import fleowo.main.mechanics.invitation.PartyInvitation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class AcceptCommand extends SinglePartyCommand {
    public AcceptCommand() {
        super("/party accept", "accept", 1, "Accept party invitation");
    }

    public void process(String[] args, Player player) {
        if (!InvitationManager.getInstance().hasInvitation(player)) {
            player.sendMessage(ChatColor.RED + "You don't have any pending party invitation");
            return;
        }
        if (PartyManager.getInstance().hasParty(player)) {
            player.sendMessage(ChatColor.RED + "This player already has a party");
            return;
        }
        PartyInvitation invitation = InvitationManager.getInstance().getInvitation(player);
        if (PartyManager.getInstance().getParty(invitation.getInvitationSender()).getMembers().size() >= 5) {
            player.sendMessage(ChatColor.RED + "The party already has the maximum amount of player");
            return;
        }
        Bukkit.getPluginManager().callEvent(new PartyJoinEvent(PartyManager.getInstance().getParty(invitation.getInvitationSender()), invitation.getInvitedPlayer()));
        InvitationManager.getInstance().acceptInvitation(InvitationManager.getInstance().getInvitation(player));
    }
}
