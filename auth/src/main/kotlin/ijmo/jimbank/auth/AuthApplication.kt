package ijmo.jimbank.auth

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer

@SpringBootApplication
@EnableAuthorizationServer
class AuthApplication

fun main(args: Array<String>) {
    runApplication<AuthApplication>(*args)
}
