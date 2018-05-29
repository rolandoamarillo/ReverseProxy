package com.reverseproxy.example.mercury.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

/**
 * Controller for an example
 *
 * @since 0.0.1
 * @version 0.0.1
 * @author <a href="rolando58604@gmail.com">Rolando Amarillo</a>
 */
@RestController
class ExampleController {

    @GetMapping("/hello")
    fun salute(): Mono<String> {
        return Mono.just("Hola!")
    }

}