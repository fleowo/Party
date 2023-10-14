package fleowo.main.mechanics;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.OfflinePlayer;

public class Party {
    private PartyMember owner;

    private ArrayList<PartyMember> members;

    public Party(PartyMember owner) {
        this.owner = owner;
        this.members = new ArrayList<>();
    }

    public boolean addMember(PartyMember player) {
        if (this.members.contains(player))
            return false;
        return this.members.add(player);
    }

    public boolean removePlayer(PartyMember player) {
        return this.members.remove(player);
    }

    public PartyMember getOwner() {
        return this.owner;
    }

    public void setNewOwner(PartyMember player) {
        this.members.add(this.owner);
        this.members.remove(player);
        this.owner = player;
    }

    public PartyMember getMember(OfflinePlayer player) {
        for (PartyMember member : this.members) {
            if (member.getOfflinePlayer().equals(player))
                return member;
        }
        return null;
    }

    public ArrayList<PartyMember> getMembers() {
        return this.members;
    }

    public List<PartyMember> getMembersAndOwner() {
        List<PartyMember> members = new ArrayList<>(this.members);
        members.add(this.owner);
        return members;
    }
}
