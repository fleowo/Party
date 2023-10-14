package fleowo.main.commands;

import org.bukkit.entity.Player;

public abstract class SinglePartyCommand {
    private String firstArg;

    private String usage;

    private int requiredLength;

    private String description;

    public SinglePartyCommand(String usage, String firstArg, int requiredLength, String description) {
        this.usage = usage;
        this.firstArg = firstArg;
        this.requiredLength = requiredLength;
        this.description = description;
    }

    public String getFirstArg() {
        return this.firstArg;
    }

    public int getRequiredLength() {
        return this.requiredLength;
    }

    public String getUsage() {
        return this.usage;
    }

    public abstract void process(String[] paramArrayOfString, Player paramPlayer);

    public boolean verifyArgs(int args) {
        return (args == this.requiredLength);
    }
}
