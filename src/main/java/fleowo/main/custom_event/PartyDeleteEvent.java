package fleowo.main.custom_event;

import fleowo.main.mechanics.Party;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PartyDeleteEvent extends Event {
    private Party party;

    public PartyDeleteEvent(Party party) {
        this.party = party;
    }

    public Party getParty() {
        return this.party;
    }

    private static final HandlerList HANDLERS = new HandlerList();

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }

    @NotNull
    public HandlerList getHandlers() {
        return HANDLERS;
    }
}
