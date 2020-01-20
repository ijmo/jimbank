package ijmo.jimbank.banking

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.EnableEurekaClient
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer

@SpringBootApplication
@EnableEurekaClient
//	(exclude = [
//	org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration::class,
//	org.springframework.boot.actuate.autoconfigure.security.servlet.ManagementWebSecurityAutoConfiguration::class
//])
@EnableResourceServer
class BankingApplication

fun main(args: Array<String>) {
	runApplication<BankingApplication>(*args)
}
