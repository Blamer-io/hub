package io.blamer.hub.pg;

import io.blamer.hub.model.Chat;
import io.blamer.hub.model.Chats;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Chats in PostgreSQL.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
@Component
public class PgChats implements Chats {

  /**
   * Database Client.
   */
  private final DatabaseClient db;

  /**
   * Ctor.
   *
   * @param dbc Database Client.
   */
  public PgChats(final DatabaseClient dbc) {
    this.db = dbc;
  }

  @Override
  public Mono<Void> add(final Chat chat) {
    return this.db.sql(
        "INSERT INTO chat (id) VALUES (:id)"
      ).bind("id", chat.id())
      .fetch()
      .first()
      .then();
  }
}
