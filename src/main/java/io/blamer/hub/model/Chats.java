package io.blamer.hub.model;

import reactor.core.publisher.Mono;

/**
 * Chats.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
public interface Chats {

  /**
   * Add new Chat.
   *
   * @param chat Chat
   * @return Mono void publisher
   */
  Mono<Void> add(Chat chat);
}
