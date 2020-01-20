package ijmo.jimbank.auth.user

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) : UserDetailsService {

    private val encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder()

    fun addUser(user: User): User? {
        user.password = encoder.encode(user.password)
        return this.userRepository.save(user)
    }

    fun updateUser(user: User): User = this.userRepository.save(user)

    fun findByUsername(username: String): User? = userRepository.findByUsername(username)

    override fun loadUserByUsername(username: String): UserDetails =
        findByUsername(username)?.let{ UserPrincipal(it) } ?: throw UsernameNotFoundException("User not found")
}
