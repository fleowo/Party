package fleowo.main.commands.list;

import fleowo.main.GlobalUtils;
import fleowo.main.commands.SinglePartyCommand;
import fleowo.main.mechanics.PartyManager;
import fleowo.main.mechanics.invitation.InvitationManager;
import fleowo.main.mechanics.invitation.PartyInvitation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InviteCommand extends SinglePartyCommand {
    public InviteCommand() {
        super(ChatColor.RED + "/party invite <player>", "invite", 2, "Invite a player in your party");
    }

    public void process(String[] args, Player player) {
        Player player1 = Bukkit.getPlayer(args[1]);
        if (!PartyManager.getInstance().hasParty(player)) {
            player.sendMessage(ChatColor.RED + "You don't have any party");
            return;
        }
        if (!PartyManager.getInstance().isPartyOwner(player)) {
            player.sendMessage(ChatColor.RED + "You are not the party owner");
            return;
        }
        if (player1 == null) {
            player.sendMessage(ChatColor.RED + "This player isn't online");
            return;
        }
        if (player1.equals(player)) {
            player.sendMessage(ChatColor.RED + "You are already in your party");
            return;
        }
        if (InvitationManager.getInstance().hasInvitation(player1)) {
            player.sendMessage(ChatColor.RED + "This player already has a pending invitation");
            return;
        }
        if (PartyManager.getInstance().hasParty(player1)) {
            player.sendMessage(ChatColor.RED + "This player already has a party");
            return;
        }
        if (PartyManager.getInstance().getParty(player).getMembers().size() >= 5) {
            player.sendMessage(ChatColor.RED + "The party already has the maximum amount of player");
            return;
        }
        PartyInvitation invitation = new PartyInvitation(player, player1);
        InvitationManager.getInstance().launchInvitation(invitation);
        GlobalUtils.sendAcceptDenyMessage(player1);
    }
}
