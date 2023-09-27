package io.blamer.hub.exceptions;

public final class TokenAlreadyExists extends RuntimeException{
    public TokenAlreadyExists(String message) {
        super(message);
    }
}
