package ijmo.jimbank.banking.security

import org.springframework.boot.autoconfigure.security.oauth2.resource.JwtAccessTokenConverterConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter
import org.springframework.security.oauth2.provider.token.DefaultTokenServices
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore

@Configuration
class ResourceServerConfig(val env: Environment) : ResourceServerConfigurerAdapter() {
    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter {
        val converter = JwtAccessTokenConverter()
        converter.setSigningKey("secret")
        converter.accessTokenConverter = JwtConverter()
        return converter
    }

    fun tokenStore(): TokenStore = JwtTokenStore(accessTokenConverter())

    fun tokenServices(): DefaultTokenServices {
        val defaultTokenServices = DefaultTokenServices()
        defaultTokenServices.setTokenStore(tokenStore())
        defaultTokenServices.setSupportRefreshToken(true)
        return defaultTokenServices
    }

    override fun configure(config: ResourceServerSecurityConfigurer) {
        config.tokenServices(tokenServices())
    }

    override fun configure(http: HttpSecurity) {
        if (env.activeProfiles.contains("dev")) {
            http.headers().frameOptions().disable()
            http.authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
        }
        http
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .authorizeRequests().anyRequest().authenticated()
    }

    internal class JwtConverter : DefaultAccessTokenConverter(), JwtAccessTokenConverterConfigurer {
        override fun configure(converter: JwtAccessTokenConverter) {
            converter.accessTokenConverter = this
        }

        override fun extractAuthentication(map: Map<String, *>?): OAuth2Authentication? {
            val auth = super.extractAuthentication(map)
            auth.details = map
            return auth
        }
    }
}