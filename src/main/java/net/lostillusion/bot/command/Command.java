package net.lostillusion.bot.command;

public abstract class Command extends CommandInfo implements CommandExecutable {
    public Command(String key, String briefDescription,String example, Parameters parameters) {
        super(key, briefDescription, example, parameters);
    }
}
