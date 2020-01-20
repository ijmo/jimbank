package ijmo.jimbank.banking.core

import org.springframework.data.repository.CrudRepository

interface AccountHistoryRepository : CrudRepository<AccountHistory, Long> {

}