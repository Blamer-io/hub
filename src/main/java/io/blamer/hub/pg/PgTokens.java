/*
 * MIT License
 *
 * Copyright (c) 2023 Blamer.io
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NON-INFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package io.blamer.hub.pg;

import io.blamer.hub.model.Token;
import io.blamer.hub.model.Tokens;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Tokens in PostgreSQL.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
@Component
public class PgTokens implements Tokens {

  /**
   * Database Client.
   */
  private final DatabaseClient db;

  /**
   * Ctor.
   *
   * @param dbc DatabaseClient
   */
  public PgTokens(final DatabaseClient dbc) {
    this.db = dbc;
  }

  @Override
  public Mono<Void> add(final Token token) {
    return this.db.sql(
        "INSERT INTO token (token, alias, chat)"
          + "VALUES (:token, :alias, :chat)"
      )
      .bind("token", token.value())
      .bind("alias", token.alias())
      .bind("chat", token.chat().id())
      .fetch()
      .first()
      .then();
  }
}
