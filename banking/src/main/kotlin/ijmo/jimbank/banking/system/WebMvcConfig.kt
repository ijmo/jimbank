package ijmo.jimbank.banking.system

import ijmo.jimbank.banking.jwt.JwtClaimResolver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {
    @Bean
    fun currentUserResolver(): JwtClaimResolver {
        return JwtClaimResolver()
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(JwtClaimResolver())
    }
}