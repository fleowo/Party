package fleowo.main.custom_event;

import fleowo.main.mechanics.Party;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PartyJoinEvent extends Event {
    private Party party;

    private OfflinePlayer player;

    public PartyJoinEvent(Party party, OfflinePlayer player) {
        this.party = party;
        this.player = player;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @NotNull
    public HandlerList getHandlers() {
        return HANDLERS;
    }

    public Party getParty() {
        return this.party;
    }

    public OfflinePlayer getPlayer() {
        return this.player;
    }
}
