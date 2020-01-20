package ijmo.jimbank.banking.lifecycle

import ijmo.jimbank.banking.core.BankingService
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class AppListener (val bankingService: BankingService) {
    @EventListener
    fun afterReady(event: ApplicationReadyEvent) {
//        bankingService.deposit()
    }
}