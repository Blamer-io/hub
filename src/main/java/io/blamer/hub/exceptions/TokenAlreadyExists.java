package io.blamer.hub.exceptions;

public class TokenAlreadyExists extends RuntimeException{
    public TokenAlreadyExists(String message) {
        super(message);
    }
}
