package net.lostillusion.bot.interfaces;

import net.lostillusion.bot.command.CommandInfo;
import net.lostillusion.bot.abstracts.UserMess;
import org.javacord.api.event.message.MessageCreateEvent;

public interface MessageScanner {
  void scanMessage(MessageCreateEvent event);
  static void logToConsole(CommandInfo command, UserMess mess) {
    //TODO: Create logger
  }
}
