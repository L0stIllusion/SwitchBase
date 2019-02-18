package net.lostillusion.bot.interfaces;

import java.util.List;
import net.lostillusion.bot.command.Command;

public interface CommandCore {
  void initCommands();
  List<Command> getCommmands();
}
