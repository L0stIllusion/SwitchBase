package net.lostillusion.bot.command;

import org.atteo.classindex.IndexSubclasses;

@IndexSubclasses
public abstract class Command extends CommandInfo implements CommandExecutable {
    public Command(String key, String briefDescription, String detailedDescription, String example, CommandCategory commandCategory, Parameters parameters) {
        super(key, briefDescription, detailedDescription, example, commandCategory, parameters);
    }
}
