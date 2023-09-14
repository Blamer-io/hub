package io.blamer.hub.controller;

import io.blamer.hub.model.Tokens;
import io.blamer.hub.rq.RequestToken;
import io.blamer.hub.rq.TokenToAdd;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static io.netty.handler.codec.http.HttpHeaderValidationUtil.validateToken;

/**
 * Token controller.
 *
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
@RestController
@RequestMapping("/api/tokens")
public class TokenController {

  /**
   * Tokens.
   */
  private final Tokens tokens;

  /**
   * Ctor.
   *
   * @param tkns Tokens
   */
  public TokenController(final Tokens tkns) {
    this.tokens = tkns;
  }

  /**
   * Registers new Token with Chat ID.
   *
   * @param request RequestToken
   * @return Mono void publisher
   * @throws Exception if something went wrong
   */
  @PostMapping
  Mono<Void> registerToken(@RequestBody final RequestToken request) throws Exception {
    String token = request.getValue();
    String secretKey = "your-secret-key";

    if (validateToken(token, secretKey)) {
      return this.tokens.add(new TokenToAdd(request).value());
    } else {
      throw new RuntimeException("Invalid token");
    }
  }

  public boolean validateToken(String token, String secretKey) {
    try {
      Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);
      return true;
    } catch (SignatureException e) {
      return false;
    }
  }
}
