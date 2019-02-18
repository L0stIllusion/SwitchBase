package net.lostillusion.bot.command;

import java.util.concurrent.CompletableFuture;
import net.lostillusion.bot.exception.Exceptionable;
import net.lostillusion.bot.internal.ExceptionEmbedPost;
import net.lostillusion.bot.abstracts.UserMess;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.Messageable;

public interface CommandBody extends Exceptionable<ExceptionEmbedPost, Messageable, CompletableFuture<Message>> {
    void executeCommand(UserMess mess, DiscordApi api) throws CommandException;
}
