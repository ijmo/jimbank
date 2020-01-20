package ijmo.jimbank.auth.user

import com.fasterxml.jackson.annotation.JsonIgnore
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import java.io.Serializable
import java.time.LocalDateTime
import javax.persistence.*
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

@Entity
@Table(name = "users")
class User : Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "username", unique = true, nullable = false)
    @Pattern(regexp = "^[a-z][a-z0-9]{1,8}$", flags = [Pattern.Flag.UNICODE_CASE])
    @NotBlank
    var username: String? = null

    @Column(name = "email")
    @NotBlank
    @Email
    var email: String? = null

    @Column(name = "password")
    @NotBlank
    var password: String? = null

    @Column(name = "authorities")
    @JsonIgnore
    private var authorities = "ROLE_USER"

    @Column(name = "created_on")
    @CreatedDate
    private var createdOn: LocalDateTime? = LocalDateTime.now()

    @Column(name = "modified_on")
    @LastModifiedDate
    private var modifiedOn: LocalDateTime? = LocalDateTime.now()

    private constructor(username: String?, password: String?, email: String?) : this() {
        this.email = email
        this.username = username
        this.password = password
    }

    constructor()

    override fun hashCode(): Int {
        return if (id != null) {
            id.hashCode()
        } else super.hashCode()
    }

    override fun equals(o: Any?): Boolean {
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val user = o as User
        if (username != user.username) return false
        if (email != user.email) return false
        return if (password != user.password) false else getAuthorities().toSet() == user.getAuthorities().toSet()
    }

    fun getAuthorities(): Collection<GrantedAuthority?> {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)
    }

    fun setAuthorities(commaSeparatedAuthorities: String) {
        authorities = commaSeparatedAuthorities
    }

    fun setAuthorities(authorities: List<String?>?) {
        this.authorities = java.lang.String.join(",", authorities)
    }

    override fun toString() =
        "User(username=$username, email=$email, password=$password, authorities=${getAuthorities()})"

    data class Builder (
        private var username: String? = null,
        private var password: String? = null,
        private var email: String? = null) {

        fun username(username: String?) = apply { this.username = username }
        fun password(password: String?) = apply { this.password = password }
        fun email(email: String?) = apply { this.email = email }
        fun build(): User = User(username, password, email)
    }
}