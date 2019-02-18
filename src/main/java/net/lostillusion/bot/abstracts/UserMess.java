package net.lostillusion.bot.abstracts;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import net.lostillusion.bot.BotBase;
import net.lostillusion.bot.database.Database;
import net.lostillusion.bot.exception.Exceptionable;
import net.lostillusion.bot.internal.ExceptionEmbedPost;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.Icon;
import org.javacord.api.entity.channel.ServerTextChannel;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.Messageable;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.entity.permission.Role;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.util.logging.ExceptionLogger;

/*
*   UserMess inspired by Azpng
*   Basically a single variable that contains anything
*   that you would need for a command
*
*   Recreated by: Lost_Illusion
*/
public abstract class UserMess implements Exceptionable<ExceptionEmbedPost, Messageable, CompletableFuture<Message>> {
  private Message message;
  private TextChannel textChannel;
  private MessageAuthor messageAuthor;
  private final User user;
  private Server server;
  private ServerTextChannel serverTextChannel;
  private User botUser;
  private DiscordApi api;
  private Collection<User> users;
  private Icon userIcon;

  public UserMess(MessageCreateEvent event){
      //Sets all variables
      user = event.getMessage().getUserAuthor().orElseThrow();
      setMessage(event);
      setTextChannel(event);
      setMessageAuthor(event);
      setServer(event);
      setBotUser(event);
      setServerTextChannel(event);
      setUsers(event);
      setUserIcon(event);
      setApi(event);
  }

  //Setters
  private void setUsers(MessageCreateEvent event) { users = event.getServer().orElseThrow().getMembers(); }

  private void setServerTextChannel(MessageCreateEvent event) { event.getServerTextChannel().ifPresent(serverTextChannel1 -> serverTextChannel = serverTextChannel1); }

  private void setMessage(MessageCreateEvent event){ message = event.getMessage(); }

  private void setTextChannel(MessageCreateEvent event) { textChannel = event.getChannel(); }

  private void setMessageAuthor(MessageCreateEvent event) { messageAuthor = event.getMessage().getAuthor(); }

  private void setServer(MessageCreateEvent event) {

      event.getServer().ifPresent(server1 -> server = server1);
  }
  private void setBotUser(MessageCreateEvent event) { botUser = event.getApi().getYourself(); }

  private void setApi(MessageCreateEvent event) {
      api = event.getApi();
  }

  private void setUserIcon(MessageCreateEvent event) { userIcon = event.getMessage().getAuthor().getAvatar(); }

  //Getters
  public DiscordApi getApi() { return api; }

  public Message getMessage() { return message; }

  public TextChannel getTextChannel() {
      return textChannel;
  }

  public MessageAuthor getMessageAuthor() {
      return messageAuthor;
  }

  public User getUser() {
      return user;
  }

  public Server getServer() {
      return server;
  }

  public ServerTextChannel getServerTextChannel() { return serverTextChannel; }

  public User getBotUser() { return botUser; }

  public CompletableFuture<Message> sendMessage(EmbedBuilder embed){
      return textChannel.sendMessage(embed).exceptionally(ExceptionLogger.get());
  }

  public CompletableFuture<Message> sendMessage(String context){
      return textChannel.sendMessage(context);
  }

  public String getAuthorDisplayName() {
      return messageAuthor.getDisplayName();
  }

  public String getMessageContent() {
      return message.getContent();
  }

  public List<User> getMentionedUsers() {
      return message.getMentionedUsers();
  }

  public List<Role> getMentionedRoles(){
      return message.getMentionedRoles();
  }

  public String getMessageContentWithoutPrefix() { return getMessageContent().substring(getPrefix().length()); }

  public String getMessageContentWithoutCommandPrefix() {
    return getMessageContent()
            .substring(getPrefix().length())
            .trim()
            .replaceFirst("\\w+\\s", ""); //removes first word (a.k.a command key)
  }

  public abstract String getPrefix();

  public Collection<User> getMembers() {
      return users;
  }

  public Icon getUserIcon() { return userIcon; }
}
