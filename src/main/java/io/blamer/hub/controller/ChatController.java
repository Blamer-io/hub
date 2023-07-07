package io.blamer.hub.controller;

import io.blamer.hub.model.Chats;
import io.blamer.hub.pg.PgChat;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * Chat Controller.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
@RestController
@RequestMapping("/api/chats")
public class ChatController {

  /**
   * Chats.
   */
  private final Chats chats;

  /**
   * Ctor.
   *
   * @param chats Chats
   */
  public ChatController(@Qualifier("validated") final Chats chats) {
    this.chats = chats;
  }

  /**
   * Register new Chat ID.
   *
   * @param chat Chat ID
   * @return Mono void publisher
   */
  @PostMapping
  public Mono<Void> registerChatId(@RequestBody final Long chat) {
    return this.chats.add(new PgChat(chat));
  }
}
