package fleowo.main.commands;

import fleowo.main.commands.list.*;

import java.util.ArrayList;
import java.util.List;

public class CommandsList {
    public static List<SinglePartyCommand> getCommands() {
        List<SinglePartyCommand> commands = new ArrayList<>();
        commands.add(new InviteCommand());
        commands.add(new CreateCommand());
        commands.add(new KickCommand());
        commands.add(new LeaveCommand());
        commands.add(new AcceptCommand());
        commands.add(new DenyCommand());
        commands.add(new ChangeOwnerCommand());
        commands.add(new SayCommand());
        return commands;
    }

    public static SinglePartyCommand getCommand(String firstArg) {
        for (SinglePartyCommand command : getCommands()) {
            if (command.getFirstArg().equalsIgnoreCase(firstArg))
                return command;
        }
        return null;
    }
}
