package net.lostillusion.bot.internal;

import java.awt.Color;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import net.lostillusion.bot.exception.ExceptionSendable;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.javacord.api.entity.message.Message;
import org.javacord.api.entity.message.Messageable;
import org.javacord.api.entity.message.embed.EmbedBuilder;

public class ExceptionEmbedPost extends EmbedBuilder implements ExceptionSendable<ExceptionEmbedPost, Messageable, CompletableFuture<Message>> {
  @Override
  public CompletableFuture<Message> post(Supplier<Messageable> output) {
    return output.get().sendMessage(this);
  }

  @Override
  public ExceptionEmbedPost get(Throwable e) {
    return (ExceptionEmbedPost)
            setDescription(
                    String.format(
                            "An error has occured during the processing of this command, please send the following to the developer using the report command! \n```%s\n```",
                            ExceptionUtils.getStackTrace(e)))
                    .setColor(new Color(158, 0, 0));
  }

  @Override
  public ExceptionEmbedPost get(String message) {
    return (ExceptionEmbedPost)
            setDescription(message)
                    .setColor(new Color(158, 0, 0));
  }
}
