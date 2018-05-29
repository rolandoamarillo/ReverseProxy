package com.reverseproxy.example.mercury.controller

import com.reverseproxy.example.mercury.repository.ProxyRepository
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class ProxyController(private val proxyRepository: ProxyRepository) {

    @GetMapping("/posts")
    @ResponseBody
    fun posts(serverHttpRequest: ServerHttpRequest, serverHttpResponse: ServerHttpResponse): Mono<Void> {
        return proxyRepository.posts(serverHttpRequest, serverHttpResponse)
    }

}