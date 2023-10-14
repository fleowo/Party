package fleowo.main.mechanics;

import java.util.ArrayList;
import org.bukkit.OfflinePlayer;

public class PartyManager {
    private static PartyManager instance = new PartyManager();

    public static final int MAX_PARTY_PLAYERS = 6;

    private ArrayList<Party> parties;

    public static PartyManager getInstance() {
        return instance;
    }

    private PartyManager() {
        this.parties = new ArrayList<>();
    }

    public void addParty(Party party) {
        this.parties.add(party);
    }

    public boolean hasParty(OfflinePlayer player) {
        for (Party party : this.parties) {
            if (party.getMembers().contains(new PartyMember(player)))
                return true;
            if (party.getOwner().equals(new PartyMember(player)))
                return true;
        }
        return false;
    }

    public Party getParty(OfflinePlayer player) {
        for (Party party : this.parties) {
            if (party.getMembers().contains(new PartyMember(player)))
                return party;
            if (party.getOwner().equals(new PartyMember(player)))
                return party;
        }
        return null;
    }

    public boolean isPartyOwner(OfflinePlayer player) {
        for (Party party : this.parties) {
            if (party.getOwner().equals(new PartyMember(player)))
                return true;
        }
        return false;
    }

    public void removeParty(OfflinePlayer player) {
        for (Party party : this.parties) {
            if (party.getMembersAndOwner().contains(new PartyMember(player))) {
                this.parties.remove(party);
                return;
            }
        }
    }

    public void updatePlayerOnConnect(OfflinePlayer player) {
        if (!hasParty(player))
            return;
        Party party = getParty(player);
        if (party.getOwner().equals(new PartyMember(player))) {
            party.setNewOwner(new PartyMember(player));
        } else if (party.getMembers().contains(new PartyMember(player))) {
            party.getMembers().remove(new PartyMember(player));
            party.getMembers().add(new PartyMember(player));
        }
    }
}
