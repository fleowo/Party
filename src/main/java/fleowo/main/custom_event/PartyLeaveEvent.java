package fleowo.main.custom_event;

import fleowo.main.mechanics.PartyMember;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PartyLeaveEvent extends Event {
    private PartyMember partyMember;

    public PartyLeaveEvent(PartyMember partyMember) {
        this.partyMember = partyMember;
    }

    public PartyMember getPartyMember() {
        return this.partyMember;
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
