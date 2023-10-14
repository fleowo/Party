package fleowo.main.mechanics.invitation;

import java.util.ArrayList;

import fleowo.main.PartyPlugin;
import fleowo.main.mechanics.Party;
import fleowo.main.mechanics.PartyManager;
import fleowo.main.mechanics.PartyMember;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class InvitationManager {
    private static InvitationManager instance = new InvitationManager();

    private ArrayList<PartyInvitation> invitations;

    public static InvitationManager getInstance() {
        return instance;
    }

    private InvitationManager() {
        this.invitations = new ArrayList<>();
    }

    public void launchInvitation(PartyInvitation invitation) {
        this.invitations.add(invitation);
        if (invitation.getInvitedPlayer().isOnline()) {
            Player player = (Player)invitation.getInvitedPlayer();
            player.sendMessage(ChatColor.GREEN + "You received an invitation to join the " + invitation.getInvitationSender().getName() + "'s party");
        }
        if (invitation.getInvitationSender().isOnline()) {
            Player player = (Player)invitation.getInvitationSender();
            player.sendMessage(ChatColor.GREEN + "Your invitation has been sent");
        }
        cancelInvitation(invitation);
    }

    public PartyInvitation getInvitation(OfflinePlayer player) {
        for (PartyInvitation invitation : this.invitations) {
            if (invitation.getInvitedPlayer().equals(player))
                return invitation;
        }
        return null;
    }

    public boolean acceptInvitation(PartyInvitation invitation) {
        if (PartyManager.getInstance().hasParty(invitation.getInvitedPlayer()))
            return false;
        Party party = PartyManager.getInstance().getParty(invitation.getInvitationSender());
        party.addMember(new PartyMember(invitation.getInvitedPlayer()));
        if (invitation.getInvitedPlayer().isOnline()) {
            Player player = (Player)invitation.getInvitedPlayer();
            player.sendMessage(ChatColor.GREEN + "You joined the " + invitation.getInvitationSender().getName() + "'s party");
        }
        if (invitation.getInvitationSender().isOnline()) {
            Player player = (Player)invitation.getInvitationSender();
            player.sendMessage(ChatColor.GREEN + invitation.getInvitedPlayer().getName() + " accepted your party invitation");
        }
        this.invitations.remove(invitation);
        return true;
    }

    public void denyInvitation(PartyInvitation invitation) {
        if (invitation.getInvitedPlayer().isOnline()) {
            Player player = (Player)invitation.getInvitedPlayer();
            player.sendMessage(ChatColor.GREEN + "You denied the " + invitation.getInvitationSender().getName() +"'s invitation");
        }
        if (invitation.getInvitationSender().isOnline()) {
            Player player = (Player)invitation.getInvitationSender();
            player.sendMessage(ChatColor.GREEN + invitation.getInvitedPlayer().getName() + " denied your party invitation");
        }
        this.invitations.remove(invitation);
    }

    public boolean hasInvitation(OfflinePlayer player) {
        for (PartyInvitation invitation : this.invitations) {
            if (invitation.getInvitedPlayer().equals(player))
                return true;
        }
        return false;
    }

    private void cancelInvitation(final PartyInvitation invitation) {
        (new Thread(() -> {
            try {
                Thread.sleep((30 * 1000L));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            (new BukkitRunnable() {
                public void run() {
                    if (InvitationManager.this.invitations.remove(invitation)) {
                        if (invitation.getInvitedPlayer().isOnline()) {
                            Player player = (Player)invitation.getInvitedPlayer();
                            player.sendMessage(ChatColor.RED + "Your party invitation expired");
                        }
                        if (invitation.getInvitationSender().isOnline()) {
                            Player player = (Player)invitation.getInvitationSender();
                            player.sendMessage(ChatColor.RED + "The party invitation for " + invitation.getInvitedPlayer().getName() + " expired");
                        }
                    }
                }
            }).runTask(PartyPlugin.getInstance());
        })).start();
    }
}
