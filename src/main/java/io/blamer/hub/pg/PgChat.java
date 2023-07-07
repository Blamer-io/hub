package io.blamer.hub.pg;

import io.blamer.hub.model.Chat;
import lombok.RequiredArgsConstructor;

/**
 * Chat in PostgreSQL.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
@RequiredArgsConstructor
public class PgChat implements Chat {

  /**
   * Chat ID.
   */
  private final long id;

  @Override
  public long id() {
    return this.id;
  }
}
