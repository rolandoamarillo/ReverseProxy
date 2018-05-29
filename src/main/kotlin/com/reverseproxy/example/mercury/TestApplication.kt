package com.reverseproxy.example.mercury

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

/**
 * Gateway api entry point
 *
 * @author <a href="amarilr@reverseproxy.com">Rolando Amarillo</a>
 * @version 0.0.1
 * @since 0.0.1
 */
@SpringBootApplication
class TestApplication

fun main(args: Array<String>) {
    runApplication<TestApplication>(*args)
}
