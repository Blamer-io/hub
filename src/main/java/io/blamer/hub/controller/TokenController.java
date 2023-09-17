package io.blamer.hub.controller;

import io.blamer.hub.model.Tokens;
import io.blamer.hub.rq.RequestToken;
import io.blamer.hub.rq.TokenToAdd;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

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
    public TokenController(@Qualifier("tokenValidated")final Tokens tkns) {
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
    public Mono<Void> registerToken(@RequestBody final RequestToken request)
            throws Exception {
        return this.tokens.add(new TokenToAdd(request).value());
    }
}
