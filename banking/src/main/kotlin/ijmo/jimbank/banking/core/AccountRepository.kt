package ijmo.jimbank.banking.core

import org.springframework.data.repository.CrudRepository

interface AccountRepository : CrudRepository<Account, Long> {
    fun findAllByOwnerId(ownerId: Long): List<Account>
    fun findByAccountNo(accountNo: String): Account?
}