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

import io.blamer.hub.model.Chat;
import io.blamer.hub.model.Token;
import lombok.RequiredArgsConstructor;

/**
 * Token in PostgreSQL.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
@RequiredArgsConstructor
public class PgToken implements Token {

  /**
   * Token ID.
   */
  private final Long id;
  /**
   * Token value.
   */
  private final String value;
  /**
   * Token alias.
   */
  private final String alias;
  /**
   * Chat ID.
   */
  private final long chat;

  @Override
  public Long id() {
    return this.id;
  }

  @Override
  public String value() {
    return this.value;
  }

  @Override
  public String alias() {
    return this.alias;
  }

  @Override
  public Chat chat() {
    return new PgChat(this.chat);
  }
}
