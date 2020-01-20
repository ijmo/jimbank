package ijmo.jimbank.banking.core

import org.springframework.stereotype.Service

@Service
class BankingService (private val accountRepository: AccountRepository) {

    fun generateNewAccountNo(): String {
        return "newAccountNo"
    }

    /**
     * 계좌 신규
     */
    fun create(ownerId: Long, amount: Double) {
        accountRepository.save(Account.Builder().accountNo(generateNewAccountNo()).balance(amount).build())
    }

    fun create(ownerId: Long) {
        create(ownerId, 0.0)
    }

    /**
     * 계좌 조회
     */
    fun list(ownerId: Long): List<Account> {
//        return accountRepository.findAllByOwnerId(ownerId)
        return emptyList()
    }

    /**
     * 계좌 상태 변경
     */
    fun update() {
//        accountRepository.
    }

    /**
     * 입금
     */
    fun deposit(accountNo: String) {
//        accountRepository.
    }

    /**
     * 출금
     */
    fun withdraw() {

    }

    /**
     * 이체
     */
    fun wire() {

    }
}
