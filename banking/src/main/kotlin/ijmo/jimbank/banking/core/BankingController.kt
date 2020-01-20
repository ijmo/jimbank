package ijmo.jimbank.banking.core

import ijmo.jimbank.banking.jwt.CurrentUser
import ijmo.jimbank.banking.user.UserInfo
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BankingController {
    @GetMapping("/")
    fun echo(@CurrentUser userInfo: UserInfo): ResponseEntity<Any> {
        return ResponseEntity("UserInfo: $userInfo", HttpStatus.OK)
    }
}