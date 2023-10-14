package fleowo.main.commands.list;

import fleowo.main.commands.SinglePartyCommand;
import fleowo.main.custom_event.PartyCreateEvent;
import fleowo.main.mechanics.Party;
import fleowo.main.mechanics.PartyManager;
import fleowo.main.mechanics.PartyMember;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class CreateCommand extends SinglePartyCommand {
    public CreateCommand() {
        super("/party create", "create", 1, "Create a party");
    }

    public void process(String[] args, Player player) {
        if (PartyManager.getInstance().hasParty(player)) {
            player.sendMessage(ChatColor.RED  + "You already have a party");
            return;
        }
        Bukkit.getPluginManager().callEvent(new PartyCreateEvent(player));
        PartyManager.getInstance().addParty(new Party(new PartyMember(player)));
        player.sendMessage(ChatColor.GREEN + "Your party has been created");
    }
}
