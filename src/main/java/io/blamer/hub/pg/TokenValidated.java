package io.blamer.hub.pg;

import io.blamer.hub.exceptions.TokenAlreadyExists;
import io.blamer.hub.model.Token;
import io.blamer.hub.model.Tokens;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
public class TokenValidated implements Tokens {
    /**
     * Tokens.
     */
    private final Tokens tokens;
    /**
     * Database Client.
     */
    private final DatabaseClient db;

    /**
     * Ctor.
     *
     * @param tks Tokens
     * @param dbc  Database Client
     */
    public TokenValidated(
        @Qualifier("pgTokens") final Tokens tks,
        final DatabaseClient dbc
    ) {
        this.tokens = tks;
        this.db = dbc;
    }

    @Override
    public Mono<Void> add(final Token token) {
        return validateToken(token)
        .then(this.db.sql("SELECT t.token AS token FROM token t WHERE t.token = :token")
                .bind("token", token.value())
                .fetch()
                .one()
                .flatMap(rows -> Mono.just((String) rows.get("token")))
                .flatMap(existingToken -> Mono.error(
                        new TokenAlreadyExists("Token %s already exists".formatted(existingToken))
                ))
                .switchIfEmpty(this.tokens.add(token))
                .then());
    }

    private Mono<Void> validateToken(Token token) {
        if (token.value().equals("valid-token")) {
            return Mono.empty();
        } else {
            return Mono.error(new IllegalArgumentException("Invalid token"));
        }
    }
}
