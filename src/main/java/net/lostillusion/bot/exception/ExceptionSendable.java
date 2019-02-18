package net.lostillusion.bot.exception;

import java.util.function.Supplier;

public interface ExceptionSendable<T extends ExceptionSendable<T, V, O>, V, O> {
  default O getAndSend(Throwable e, Supplier<V> supplier) {
    return get(e).post(supplier);
  }
  T get(Throwable e);
  default O getAndSend(String message, Supplier<V> supplier) {
    return get(message).post(supplier);
  }
  T get(String message);
  O post(Supplier<V> output);
}
