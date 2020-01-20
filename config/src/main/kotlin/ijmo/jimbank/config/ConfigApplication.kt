package ijmo.jimbank.config

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer
import org.springframework.cloud.netflix.eureka.EnableEurekaClient

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
class ConfigApplication

fun main(args: Array<String>) {
    runApplication<ConfigApplication>(*args)
}
