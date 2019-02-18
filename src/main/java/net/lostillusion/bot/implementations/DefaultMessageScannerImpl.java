package net.lostillusion.bot.implementations;

import net.lostillusion.bot.BotBase;
import net.lostillusion.bot.interfaces.MessageScanner;
import net.lostillusion.bot.abstracts.UserMess;
import org.javacord.api.event.message.MessageCreateEvent;

public class DefaultMessageScannerImpl implements MessageScanner {
  @Override
  public void scanMessage(MessageCreateEvent event) {
    if(!event.isServerMessage() || !event.getMessageAuthor().isUser()) return;
    UserMess mess = BotBase.getUserMess(event).orElseThrow(AssertionError::new); //Something is seriously wrong if an exception is thrown
    if(mess.getPrefix() != null)  {
      new DefaultCommandCoreImpl().getCommmands().stream()
              .filter(command -> mess.getMessageContentWithoutPrefix().split(" ")[0].equalsIgnoreCase(command.getKey()))
              .findFirst()
              .ifPresent(command -> command.execute(mess, BotBase.getApi()));
    }
  }
}
