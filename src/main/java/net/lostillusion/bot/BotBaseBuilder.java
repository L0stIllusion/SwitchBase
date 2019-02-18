package net.lostillusion.bot;

import java.util.function.Supplier;
import net.lostillusion.bot.abstracts.UserMess;
import net.lostillusion.bot.database.Database;
import net.lostillusion.bot.implementations.DefaultCommandCoreImpl;
import net.lostillusion.bot.implementations.DefaultMessageScannerImpl;
import net.lostillusion.bot.implementations.DefaultUserMessImpl;
import net.lostillusion.bot.interfaces.CommandCore;
import net.lostillusion.bot.interfaces.MessageScanner;
import org.javacord.api.DiscordApi;

public class BotBaseBuilder {
  private DiscordApi api;
  private CommandCore commandCore = new DefaultCommandCoreImpl();
  private MessageScanner messageScanner = new DefaultMessageScannerImpl();
  private Database database = null;
  private String prefix;
  private Class<? extends UserMess> mess = DefaultUserMessImpl.class;
  private BotBaseBuilder() {}

  /**
   * Handle log in.
   * @param dapi Discord Api Instance
   * @return BotBaseBuilder to chain method calls
   */
  public static BotBaseBuilder withApi(Supplier<DiscordApi> dapi) {
    BotBaseBuilder builder = new BotBaseBuilder();
    builder.api = dapi.get();
    return builder;
  }

  /**
   * Handle log in.
   * @param dapi Discord Api Instance
   * @return BotBaseBuilder to chain method calls
   */
  public static BotBaseBuilder withApi(DiscordApi dapi) {
    BotBaseBuilder builder = new BotBaseBuilder();
    builder.api = dapi;
    return builder;
  }

  public BotBaseBuilder setCommandCore(CommandCore implementation) {
    commandCore = implementation;
    return this;
  }

  public BotBaseBuilder setMessageScanner(MessageScanner implementation) {
    messageScanner = implementation;
    return this;
  }

  public BotBaseBuilder setDatabase(Database database) {
    this.database = database;
    return this;
  }

  public BotBaseBuilder setPrefix(String prefix) {
    this.prefix = prefix;
    return this;
  }

  public BotBaseBuilder setUserMess(Class<? extends UserMess> mess) {
    this.mess = mess;
    return this;
  }

  public BotBase build() {
    return new BotBase(api, prefix, commandCore, messageScanner, database, mess);
  }
}
