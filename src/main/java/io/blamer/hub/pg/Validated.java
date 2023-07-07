package io.blamer.hub.pg;

import io.blamer.hub.model.Chat;
import io.blamer.hub.model.Chats;
import io.blamer.hub.rq.ChatAlreadyExists;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

/**
 * Validated Chats.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
@Component
public class Validated implements Chats {

  /**
   * Chats.
   */
  private final Chats chats;
  /**
   * Database Client.
   */
  private final DatabaseClient db;

  /**
   * Ctor.
   *
   * @param chts Chats
   * @param dbc  Database Client
   */
  public Validated(
    @Qualifier("pgChats") final Chats chts,
    final DatabaseClient dbc
  ) {
    this.chats = chts;
    this.db = dbc;
  }

  @Override
  public Mono<Void> add(final Chat chat) {
    return this.db.sql("SELECT c.id AS id FROM chat c WHERE c.id = :chat")
      .bind("chat", chat.id())
      .fetch()
      .one()
      .flatMap(rows ->
        Mono.just((Long) rows.get("id")))
      .flatMap(
        (Function<Long, Mono<Void>>) id ->
          Mono.error(
            new ChatAlreadyExists(
              "Chat ID %s already exists"
                .formatted(id)
            )
          )
      ).switchIfEmpty(this.chats.add(chat));
  }
}
