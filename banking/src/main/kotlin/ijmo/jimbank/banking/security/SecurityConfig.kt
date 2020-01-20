package ijmo.jimbank.banking.security

import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter


//class SecurityConfig : WebSecurityConfigurerAdapter() {
//
//    override fun configure(http: HttpSecurity) {
//        http.authorizeRequests()
//            .antMatchers("/**")
//            .permitAll()
//            .anyRequest()
//            .authenticated()
//            .and()
//            .csrf()
//            .disable()
//    }
//}