package net.lostillusion.bot.command;

import java.util.logging.Logger;
import net.lostillusion.bot.interfaces.MessageScanner;
import net.lostillusion.bot.internal.ExceptionEmbedPost;
import net.lostillusion.bot.abstracts.UserMess;
import org.javacord.api.DiscordApi;

public interface CommandExecutable extends CommandBody {
  default void execute(UserMess mess, DiscordApi api) {
    try {
      if(this instanceof CommandInfo) {
        MessageScanner.logToConsole((CommandInfo) this, mess);
        executeCommand(mess, api);
      } else {
        Logger.getGlobal().severe("Command does not have any info! ("+mess.getMessage()+")");
      }
    } catch (RuntimeException e) {
      exceptionallySend(e, mess::getTextChannel, ExceptionEmbedPost::new);
    }
  }
}

