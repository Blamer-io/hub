package io.blamer.hub.controller;

import io.blamer.hub.rq.ChatAlreadyExists;
import io.github.eocqrs.eokson.Jocument;
import io.github.eocqrs.eokson.MutableJson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Aliaksei Bialiauski (abialiauski.dev@gmail.com)
 * @since 0.0.0
 */
@Slf4j
@RestControllerAdvice
public class HubAdvice {

  /**
   * Handle {@link ChatAlreadyExists}.
   *
   * @param ex ChatAlreadyExists
   * @return ResponseEntity
   */
  @ExceptionHandler(ChatAlreadyExists.class)
  public ResponseEntity<byte[]> handle(final ChatAlreadyExists ex) {
    log.debug("Chat ID already exists", ex);
    return new ResponseEntity<>(
      new Jocument(
        new MutableJson()
          .with("code", HttpStatus.CONFLICT.value())
          .with("message", ex.getMessage())
      ).byteArray(),
      HttpStatus.CONFLICT
    );
  }
}
