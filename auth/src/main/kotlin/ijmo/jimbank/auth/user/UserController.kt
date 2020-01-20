package ijmo.jimbank.auth.user

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
class UserController(val userService: UserService) {

    @PostMapping("/signup")
    fun signup(@Valid @RequestBody u: User, result: BindingResult): ResponseEntity<Any> {
        if (result.hasErrors()) {
            return ResponseEntity(result.allErrors.map { it.defaultMessage }.joinToString(","), HttpStatus.BAD_REQUEST)
        }
        if (userService.findByUsername(u.username!!) != null) {
            return ResponseEntity(HttpStatus.BAD_REQUEST)
        }
        val newUser = User.Builder().username(u.username).email(u.email).password(u.password).build()
        userService.addUser(newUser) ?: return ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)
        return ResponseEntity(HttpStatus.CREATED)
    }
}
