package io.blamer.hub.model;

import reactor.core.publisher.Mono;

/**
 * Tokens.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
public interface Tokens {

  /**
   * Register new token.
   *
   * @param token Token
   * @return Mono void publisher
   */
  Mono<Void> add(Token token);
}
