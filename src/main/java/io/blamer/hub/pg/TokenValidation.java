package io.blamer.hub.pg;

import io.blamer.hub.exceptions.TokenAlreadyExists;
import io.blamer.hub.model.Token;
import io.blamer.hub.model.Tokens;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
@Component
public class TokenValidation implements Tokens {
    /**
     * Database Client.
     */
    private final DatabaseClient db;
    /**
     * Token insertion.
     */
    private final TokenInsertion tokenInsertion;

    /**
     * Ctor.
     *
     * @param tks Tokens
     * @param dbc Database Client
     * @param ti  Token Insertion
     */
    public TokenValidation(
            @Qualifier("pgTokens") final Tokens tks,
            final DatabaseClient dbc,
            final TokenInsertion ti
    ) {
        /**
         * Tokens.
         */
        this.db = dbc;
        this.tokenInsertion = ti;
    }
    @Override
    public Mono<Void> add(final Token token) {
        return validateAndInsertToken(token);
    }

    private Mono<Void> validateAndInsertToken(Token token) {
        return validateToken(token)
                .then(checkIfTokenExists(token))
                .then(this.tokenInsertion.add(token));
    }

    private Mono<Void> validateToken(Token token) {
        if (token.value().equals("valid-token")) {
            return Mono.empty();
        } else {
            return Mono.error(new IllegalArgumentException("Invalid token"));
        }
    }

    private Mono<Void> checkIfTokenExists(Token token) {
        return this.db.sql("SELECT t.token AS token FROM token t WHERE t.token = :token")
                .bind("token", token.value())
                .fetch()
                .one()
                .flatMap(rows -> Mono.just((String) rows.get("token")))
                .flatMap(existingToken -> Mono.error(
                        new TokenAlreadyExists("Token %s already exists".formatted(existingToken))
                ));
    }
}
