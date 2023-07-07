package io.blamer.hub.model;

/**
 * Token.
 *
 * @author Aliaksei Bialiauski ()
 * @since 0.0.0
 */
public interface Token {

  /**
   * Id.
   *
   * @return Token id
   */
  Long id();

  /**
   * Value.
   *
   * @return Token value
   */
  String value();

  /**
   * Alias.
   *
   * @return Token alias
   */
  String alias();

  /**
   * Chat.
   *
   * @return Token chat
   */
  Chat chat();
}
