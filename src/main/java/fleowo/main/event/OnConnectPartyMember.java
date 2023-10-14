package fleowo.main.event;

import fleowo.main.mechanics.PartyManager;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class OnConnectPartyMember implements Listener {
    @EventHandler
    public void onConnect(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PartyManager.getInstance().updatePlayerOnConnect((OfflinePlayer)player);
    }
}
