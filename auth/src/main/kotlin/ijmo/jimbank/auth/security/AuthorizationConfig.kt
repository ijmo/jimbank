package ijmo.jimbank.auth.security

import ijmo.jimbank.auth.user.UserPrincipal
import ijmo.jimbank.auth.user.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken
import org.springframework.security.oauth2.common.OAuth2AccessToken
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer
import org.springframework.security.oauth2.provider.OAuth2Authentication
import org.springframework.security.oauth2.provider.token.TokenEnhancer
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain
import org.springframework.security.oauth2.provider.token.TokenStore
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore
import java.util.*
import java.util.concurrent.TimeUnit

@Configuration
class AuthorizationConfig(val authenticationManager: AuthenticationManager,
                          val userService: UserService) : AuthorizationServerConfigurerAdapter() {
    @Bean
    fun accessTokenConverter(): JwtAccessTokenConverter {
        val converter = JwtAccessTokenConverter()
        converter.setSigningKey("secret")
        return converter
    }

    fun tokenStore(): TokenStore = JwtTokenStore(accessTokenConverter())

    fun tokenEnhancer(): TokenEnhancer = TokenEnhancer { accessToken: OAuth2AccessToken,
                                                         authentication: OAuth2Authentication ->
        val user = (authentication.principal as UserPrincipal).user
        val info = HashMap<String, Any>()
        info["user_id"] = user.id!!.toLong()
        (accessToken as DefaultOAuth2AccessToken).additionalInformation = info
        accessToken
    }

    override fun configure(clients: ClientDetailsServiceConfigurer) {
        clients.inMemory()
            .withClient("clientId")
            .secret(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("defaultPw"))
            .authorizedGrantTypes("password", "refresh_token")
            .scopes("read", "write")
            .accessTokenValiditySeconds(10000)//TimeUnit.MINUTES.toSeconds(30).toInt())
            .refreshTokenValiditySeconds(TimeUnit.HOURS.toSeconds(24).toInt())
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer) {
        val chain = TokenEnhancerChain()
        chain.setTokenEnhancers(listOf(tokenEnhancer(), accessTokenConverter()))
        endpoints
            .tokenStore(tokenStore())
            .tokenEnhancer(chain)
//            .accessTokenConverter(accessTokenConverter())
            .authenticationManager(authenticationManager)
            .userDetailsService(userService)
    }
}