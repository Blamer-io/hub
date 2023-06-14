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

package io.blamer.hub.controller;

import io.blamer.hub.dto.RegistryRequest;
import io.blamer.hub.dto.RegistryResponse;
import io.blamer.hub.dto.TelegramUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@RestController
@RequestMapping("/api/v1/hub")
@RequiredArgsConstructor
public class HubController {

  private final WebClient webClient;

  @ResponseStatus(HttpStatus.ACCEPTED)
  @PostMapping(value = "/auth")
  public Mono<RegistryResponse> registryToken(@RequestBody Mono<RegistryRequest> registry) {
    return registry
      .doOnNext(reg -> log.info("Sending {} to notifications service...", reg))
      .map(request -> new TelegramUser(request.getToken(), request.getChat()))
      .log()
      .publishOn(Schedulers.boundedElastic())
      .doOnNext(
        user ->
          this.webClient.post()
            .uri("/api/v1/notifications")
            .contentType(MediaType.APPLICATION_JSON)
            .body(Mono.just(user), TelegramUser.class)
            .retrieve()
            .bodyToMono(Void.class)
            .subscribe()
      )
      .map(
        reg ->
          new RegistryResponse(
            "Your token sent to authentication",
            reg.getChat()
          )
      );
  }
}
