package com.reverseproxy.example.mercury.repository

import com.reverseproxy.example.mercury.client.impl.DefaultWebClientImpl
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


/**
 * Controller for the proxy
 *
 * @since 0.0.1
 * @version 0.0.1
 * @author <a href="rolando58604@gmail.com">Rolando Amarillo</a>
 */
@RestController
class ProxyRepository(private val defaultWebClient: DefaultWebClientImpl) {

    fun posts(serverHttpRequest: ServerHttpRequest, serverHttpResponse: ServerHttpResponse): Mono<Void> {
        return defaultWebClient.get()
                .uri("http://jsonplaceholder.typicode.com/posts")
                .headers({ serverHttpRequest.headers })
                .exchange().flatMap({ clientResponse ->
                    serverHttpResponse.statusCode = clientResponse.statusCode()
                    serverHttpResponse.headers.addAll(clientResponse.headers().asHttpHeaders())
                    clientResponse.body { clientHttpResponse, _ -> serverHttpResponse.writeWith(clientHttpResponse.body) }
                })
    }

}