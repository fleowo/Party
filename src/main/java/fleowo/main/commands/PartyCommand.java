package fleowo.main.commands;

import fleowo.main.gui.PartyInventory;
import fleowo.main.mechanics.PartyManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PartyCommand implements CommandExecutor {
    public static final String PARTY_COMMAND = "party";

    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (command.getName().equalsIgnoreCase("party")) {
            if (!(commandSender instanceof Player))
                return false;
            Player player = (Player)commandSender;
            if (args.length > 0) {
                for (SinglePartyCommand command1 : CommandsList.getCommands()) {
                    if (command1.getFirstArg().equalsIgnoreCase(args[0])) {
                        if (command1.verifyArgs(args.length)) {
                            command1.process(args, player);
                        } else {
                            player.sendMessage(ChatColor.GRAY + command1.getFirstArg() + " : " + command1.getUsage());
                        }
                        return true;
                    }
                }
                provideHelp(player);
            } else if (PartyManager.getInstance().hasParty(player)) {
                PartyInventory.giveInventory(player);
            } else {
                provideHelp(player);
            }
        }
        return false;
    }

    private static void provideHelp(Player player) {
        player.sendMessage(ChatColor.YELLOW + "Available commands :");
        for (SinglePartyCommand command1 : CommandsList.getCommands())
            player.sendMessage(ChatColor.GRAY + command1.getFirstArg() + ChatColor.GRAY + " : " + ChatColor.RED + command1.getUsage());
    }
}
