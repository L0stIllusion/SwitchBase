package net.lostillusion.bot.implementations;

import net.lostillusion.bot.BotBase;
import net.lostillusion.bot.abstracts.UserMess;
import org.javacord.api.event.message.MessageCreateEvent;

public class DefaultUserMessImpl extends UserMess {
  public DefaultUserMessImpl(MessageCreateEvent event) {
    super(event);
  }

  @Override
  public String getPrefix() {
    return BotBase.getPrefix();
  }
}
