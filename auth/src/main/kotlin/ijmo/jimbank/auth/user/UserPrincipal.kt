package ijmo.jimbank.auth.user

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal(val user: User): UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority?>? = user.getAuthorities()
    override fun getPassword(): String? = user.password
    override fun getUsername(): String? = user.username
    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = true
}
