package io.blamer.hub.rq;

/**
 * Chat already exists.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
public class ChatAlreadyExists extends RuntimeException {

  /**
   * Ctor.
   */
  public ChatAlreadyExists() {
  }

  /**
   * Ctor.
   *
   * @param message Message
   */
  public ChatAlreadyExists(final String message) {
    super(message);
  }

  /**
   * Ctor.
   *
   * @param message Message
   * @param cause   Exception
   */
  public ChatAlreadyExists(final String message, final Throwable cause) {
    super(message, cause);
  }

  /**
   * Ctor.
   *
   * @param cause Exception
   */
  public ChatAlreadyExists(final Throwable cause) {
    super(cause);
  }
}
