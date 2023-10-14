package fleowo.main;

import fleowo.main.commands.PartyCommand;
import fleowo.main.commands.PartyTabCompleter;
import fleowo.main.event.InventoryClickEvent;
import fleowo.main.event.OnConnectPartyMember;
import org.bukkit.plugin.java.JavaPlugin;

public class PartyPlugin extends JavaPlugin {
    private static PartyPlugin instance;

    public static PartyPlugin getInstance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        getCommand("party").setExecutor(new PartyCommand());
        getCommand("party").setTabCompleter(new PartyTabCompleter());
        getServer().getPluginManager().registerEvents(new InventoryClickEvent(), this);
        getServer().getPluginManager().registerEvents(new OnConnectPartyMember(), this);
    }
}
