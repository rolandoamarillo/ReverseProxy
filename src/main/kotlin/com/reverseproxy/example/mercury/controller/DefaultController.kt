package com.reverseproxy.example.mercury.controller

import com.reverseproxy.example.mercury.client.api.DefaultWebClient
import org.springframework.http.MediaType
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono


/**
 * Default controller
 *
 * @since 0.0.1
 * @version 0.0.1
 * @author <a href="rolando58604@gmail.com">Rolando Amarillo</a>
 */
@RestController
class DefaultController(private val defaultWebClient: DefaultWebClient) {

    @RequestMapping("/**/**", method = [RequestMethod.GET])
    @ResponseBody
    fun defaultGetHandler(serverHttpRequest: ServerHttpRequest, serverHttpResponse: ServerHttpResponse): Mono<Void> {
        return executeRequest(defaultWebClient.get(), serverHttpRequest, serverHttpResponse)
    }

    @RequestMapping("/**/**", method = [RequestMethod.POST])
    @ResponseBody
    fun defaultPostHandler(serverHttpRequest: ServerHttpRequest, serverHttpResponse: ServerHttpResponse): Mono<Void> {
        return executeRequest(defaultWebClient.post(), serverHttpRequest, serverHttpResponse)
    }

    private fun executeRequest(requestBodyUriSpec: WebClient.RequestHeadersUriSpec<*>, serverHttpRequest: ServerHttpRequest, serverHttpResponse: ServerHttpResponse): Mono<Void> {
        val path = serverHttpRequest.path.value()
        return requestBodyUriSpec.uri(path).headers({ serverHttpRequest.headers })
                .accept(MediaType.APPLICATION_JSON)
                .exchange().flatMap({ clientResponse ->
                    serverHttpResponse.statusCode = clientResponse.statusCode()
                    serverHttpResponse.headers.addAll(clientResponse.headers().asHttpHeaders())
                    clientResponse.body { clientHttpResponse, _ -> serverHttpResponse.writeWith(clientHttpResponse.body) }
                })
    }
}