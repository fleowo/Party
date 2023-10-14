package fleowo.main.custom_event;

import org.bukkit.OfflinePlayer;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class PartyCreateEvent extends Event {
    private OfflinePlayer owner;

    public PartyCreateEvent(OfflinePlayer owner) {
        this.owner = owner;
    }

    public OfflinePlayer getOwner() {
        return this.owner;
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
