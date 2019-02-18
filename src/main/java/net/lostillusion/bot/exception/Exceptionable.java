package net.lostillusion.bot.exception;

import java.util.function.Supplier;

/**
 * @param <T> The type of ExceptionSendable
 * @param <V> The type of object to execute T with
 * @param <O> The type of object which is to be returned when T's post method is ran
 */
public interface Exceptionable<T extends ExceptionSendable<T, V, O>, V, O> {
  default T exceptionally(Throwable e, Supplier<T> instance) {
    return instance.get().get(e);
  }
  default O exceptionallySend(Throwable e, Supplier<V> supplier, Supplier<T> instance) {
    return instance.get().get(e).post(supplier);
  }
  default T exceptionally(String message, Supplier<T> instance) {
    return instance.get().get(message);
  }
  default O exceptionallySend(String message, Supplier<V> supplier, Supplier<T> instance) {
    return instance.get().get(message).post(supplier);
  }
}
