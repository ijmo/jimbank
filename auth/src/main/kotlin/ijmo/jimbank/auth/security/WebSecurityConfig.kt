package ijmo.jimbank.auth.security

import ijmo.jimbank.auth.user.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories

@Configuration
class WebSecurityConfig(val env: Environment,
                        val userService: UserService) : WebSecurityConfigurerAdapter() {

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()
        val authProvider = DaoAuthenticationProvider()
        authProvider.setUserDetailsService(userService)
        authProvider.setPasswordEncoder(encoder)
        return authProvider
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        auth?.authenticationProvider(authenticationProvider())
    }

    override fun configure(http: HttpSecurity?) {
        if (env.activeProfiles.contains("dev")) {
            http!!.headers().frameOptions().disable()
                .and().authorizeRequests()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/actuator/**").permitAll()
        }

        http!!.csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().authorizeRequests()
            .antMatchers("/signup").permitAll()
    }

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager = super.authenticationManagerBean()
}