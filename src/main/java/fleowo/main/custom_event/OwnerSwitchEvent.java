package fleowo.main.custom_event;

import fleowo.main.mechanics.PartyMember;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class OwnerSwitchEvent extends Event {
    private PartyMember newOwner;

    private PartyMember lastOwner;

    public OwnerSwitchEvent(PartyMember newOwner, PartyMember lastOwner) {
        this.newOwner = newOwner;
        this.lastOwner = lastOwner;
    }

    public PartyMember getNewOwner() {
        return this.newOwner;
    }

    public PartyMember getLastOwner() {
        return this.lastOwner;
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
