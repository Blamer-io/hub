package io.blamer.hub.pg;

import io.blamer.hub.model.Token;
import io.blamer.hub.model.Tokens;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
public class TokenInsertion implements Tokens {
    /**
     * Tokens.
     */
    private final Tokens tokens;
    /**
     * Ctor.
     *
     * @param tks Tokens
     */
    public TokenInsertion(
        @Qualifier("pgTokens")
        final Tokens tks
    ) {
        this.tokens = tks;
    }
    @Override
    public Mono<Void> add(final Token token) {
        return this.tokens.add(token);
    }
}
