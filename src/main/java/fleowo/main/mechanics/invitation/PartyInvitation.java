package fleowo.main.mechanics.invitation;

import org.bukkit.OfflinePlayer;

public class PartyInvitation {
    private OfflinePlayer invitationSender;

    private OfflinePlayer invitedPlayer;

    public PartyInvitation(OfflinePlayer invitationSender, OfflinePlayer invitedPlayer) {
        this.invitationSender = invitationSender;
        this.invitedPlayer = invitedPlayer;
    }

    public OfflinePlayer getInvitationSender() {
        return this.invitationSender;
    }

    public OfflinePlayer getInvitedPlayer() {
        return this.invitedPlayer;
    }
}
