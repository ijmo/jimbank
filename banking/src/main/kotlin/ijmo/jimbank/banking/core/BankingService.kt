package ijmo.jimbank.banking.core

import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class BankingService (private val accountRepository: AccountRepository) {

    fun generateNewAccountNo(): String {
        return "newAccountNo"
    }

    /**
     * 계좌 신규
     */
    fun create(ownerId: Long, amount: Double): Account? {
        return accountRepository.save(Account.Builder().accountNo(generateNewAccountNo()).balance(amount).build())
    }

    fun create(ownerId: Long): Account? {
        return create(ownerId, 0.0)
    }

    /**
     * 계좌 조회
     */
    fun list(ownerId: Long): List<Account> {
        return accountRepository.findAllByOwnerId(ownerId)
    }

    fun findByAccountNo(accountNo: String): Account? = accountRepository.findByAccountNo(accountNo)

    /**
     * 계좌 상태 변경
     */
    @Transactional
    fun update(account: Account, enabled: Boolean): Account? {
        account.enabled = enabled
        return accountRepository.save(account)
    }

    @Transactional
    fun update(accountNo: String, enabled: Boolean): Account? {
        val account = findByAccountNo(accountNo) ?: throw AccountNotFoundException("")
        return update(account, enabled)
    }

    /**
     * 입금
     */
    @Transactional
    fun deposit(account: Account, amount: Double): Account? {
        account.balance = account.balance ?: 0 + amount
        return accountRepository.save(account)
    }

    @Transactional
    fun deposit(accountNo: String, amount: Double): Account? {
        val account = findByAccountNo(accountNo) ?: throw AccountNotFoundException("")
        return deposit(account, amount)
    }

    /**
     * 출금
     */
    @Transactional
    fun withdraw(account: Account, amount: Double): Account? {
        if (account.balance == null || account.balance!! < amount) {
            throw NotEnoughMoneyException("")
        }
        account.balance = account.balance!! - amount
        return accountRepository.save(account)
    }

    @Transactional
    fun withdraw(accountNo: String, amount: Double): Account? {
        val account = findByAccountNo(accountNo) ?: throw AccountNotFoundException("")
        return withdraw(account, amount)
    }

    /**
     * 이체
     */
    @Transactional
    fun wire(accountSending: Account, accountReceiving: Account, amount: Double): Account? {
        if (accountSending.balance == null || accountSending.balance!! < amount) {
            throw NotEnoughMoneyException("")
        }
        accountSending.balance = accountSending.balance!! - amount
        accountReceiving.balance = accountReceiving.balance ?: 0 + amount
        accountRepository.save(accountSending)
        accountRepository.save(accountReceiving)
        return accountSending
    }

    @Transactional
    fun wire(accountNoSending: String, accountNoReceiving: String, amount: Double): Account? {
        val accountSending = findByAccountNo(accountNoSending) ?: throw AccountNotFoundException("")
        val accountReceiving = findByAccountNo(accountNoReceiving) ?: throw AccountNotFoundException("")
        return wire(accountSending, accountReceiving, amount)
    }
}
