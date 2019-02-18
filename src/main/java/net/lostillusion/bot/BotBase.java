package net.lostillusion.bot;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.logging.Logger;
import net.lostillusion.bot.abstracts.UserMess;
import net.lostillusion.bot.database.Database;
import net.lostillusion.bot.interfaces.CommandCore;
import net.lostillusion.bot.interfaces.MessageScanner;
import org.javacord.api.DiscordApi;
import org.javacord.api.event.message.MessageCreateEvent;

public class BotBase {
  private static DiscordApi api;
  private static CommandCore commandCore;
  private static MessageScanner messageScanner;
  private static Database database;
  private static String prefix;
  private static Class<? extends UserMess> mess;
  public BotBase(DiscordApi api, String prefix, CommandCore commandCore, MessageScanner messageScanner, Database database, Class<? extends UserMess> mess) {
    BotBase.api = api;
    BotBase.commandCore = commandCore;
    BotBase.messageScanner = messageScanner;
    BotBase.database = database;
    BotBase.prefix = prefix;
    BotBase.mess = mess;
    commandCore.initCommands();
    api.addMessageCreateListener(messageScanner::scanMessage);
  }

  public static DiscordApi getApi() {
    return api;
  }

  public static CommandCore getCommandCore() {
    return commandCore;
  }

  public static MessageScanner getMessageScanner() {
    return messageScanner;
  }

  public static Database getDatabase() {
    return database;
  }

  public static String getPrefix() {
    return prefix;
  }

  public static Optional<UserMess> getUserMess(MessageCreateEvent event) {
    try {
      return Optional.ofNullable(mess.getConstructor(MessageCreateEvent.class).newInstance(event));
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
      Logger.getGlobal().severe("Can't create UserMess object!");
      return Optional.empty();
    }
  }
}
