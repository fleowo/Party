package fleowo.main.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PartyTabCompleter implements TabCompleter {
    @Nullable
    public List<String> onTabComplete(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {
        if (args.length == 1)
            return new ArrayList<>(CommandsList.getCommands().stream().map(SinglePartyCommand::getFirstArg).collect(Collectors.toList()));
        SinglePartyCommand command1 = CommandsList.getCommand(args[0]);
        if (args.length == 2 && command1 != null && command1.getRequiredLength() > 1 &&
                command1.getUsage().split(" ")[2].equalsIgnoreCase("<player>"))
            return Bukkit.getOnlinePlayers().stream().map(OfflinePlayer::getName).collect(Collectors.toList());
        return new ArrayList<>();
    }
}
