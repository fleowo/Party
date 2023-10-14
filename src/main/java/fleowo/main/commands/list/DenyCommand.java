package fleowo.main.commands.list;

import fleowo.main.commands.SinglePartyCommand;
import fleowo.main.mechanics.invitation.InvitationManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class DenyCommand extends SinglePartyCommand {
    public DenyCommand() {
        super("/party deny", "deny", 1, "Deny party invitation");
    }

    public void process(String[] args, Player player) {
        if (!InvitationManager.getInstance().hasInvitation(player)) {
            player.sendMessage(ChatColor.RED + "You don't have any pending party invitation");
            return;
        }
        InvitationManager.getInstance().denyInvitation(InvitationManager.getInstance().getInvitation(player));
    }
}
