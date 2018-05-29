package com.reverseproxy.example.mercury.client.impl

import com.reverseproxy.example.mercury.client.api.DefaultWebClient
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
@Qualifier("DefaultWebClientService")
class DefaultWebClientImpl : DefaultWebClient {

    val webClient: WebClient = WebClient.create()
    // Proxyed WebClient
    // WebClient.builder().clientConnector(connector).build()
    //
    // private final val connector = ReactorClientHttpConnector { options ->
    // options.httpProxy { addressSpec -> addressSpec.host("").port(8080) }
    // }

    override fun mutate(): WebClient.Builder {
        return webClient.mutate()
    }

    override fun put(): WebClient.RequestBodyUriSpec {
        return webClient.put()
    }

    override fun head(): WebClient.RequestHeadersUriSpec<*> {
        return webClient.head()
    }

    override fun options(): WebClient.RequestHeadersUriSpec<*> {
        return webClient.options()
    }

    override fun method(method: HttpMethod): WebClient.RequestBodyUriSpec {
        return webClient.method(method)
    }

    override fun patch(): WebClient.RequestBodyUriSpec {
        return webClient.patch()
    }

    override fun get(): WebClient.RequestHeadersUriSpec<*> {
        return webClient.get()
    }

    override fun post(): WebClient.RequestBodyUriSpec {
        return webClient.post()
    }

    override fun delete(): WebClient.RequestHeadersUriSpec<*> {
        return webClient.delete()
    }
}