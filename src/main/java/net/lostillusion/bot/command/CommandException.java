package net.lostillusion.bot.command;

public class CommandException extends RuntimeException {
  public CommandException(Throwable cause) {
    super(cause);
  }

  public CommandException(String message) {
    super(message);
  }
}
