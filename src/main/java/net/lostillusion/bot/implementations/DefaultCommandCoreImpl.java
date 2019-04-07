package net.lostillusion.bot.implementations;

import java.util.List;
import net.lostillusion.bot.command.Command;
import net.lostillusion.bot.interfaces.CommandCore;

public class DefaultCommandCoreImpl implements CommandCore {
    private List<Command> commands;

    public DefaultCommandCoreImpl(Command... commands) {
      this.commands = List.of(commands);
    }

    @Override
    public List<Command> getCommands() {
      return commands;
    }
}
