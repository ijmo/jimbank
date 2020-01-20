package ijmo.jimbank.zuul

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ZuulApplication

fun main(args: Array<String>) {
	runApplication<ZuulApplication>(*args)
}
