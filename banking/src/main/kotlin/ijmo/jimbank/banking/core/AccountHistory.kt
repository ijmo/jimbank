package ijmo.jimbank.banking.core

import ijmo.jimbank.banking.model.BaseEntity
import org.springframework.data.annotation.CreatedDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "accountHistory")
class AccountHistory : BaseEntity {
    @Column(name = "account_no")
    var accountNo: String? = null

    @Column(name = "account_no_received")
    var accountNoReceived: String? = null

    @Column(name = "amount")
    var amount: Double? = null

    @Column(name = "created_on")
    @CreatedDate
    var createdOn: LocalDateTime? = LocalDateTime.now()

    private constructor(accountNo: String?, accountNoReceived: String?, amount: Double?) : this() {
        this.accountNo = accountNo
        this.accountNoReceived = accountNoReceived
        this.amount = amount
    }

    constructor()

    override fun toString() = "Account(accountNo=$accountNo, accountNoReceived=$accountNoReceived, amount=$amount)"

    data class Builder(
        private var accountNo: String? = null,
        private var accountNoReceived: String? = null,
        private var amount: Double? = null) {

        fun accountNo(accountNo: String) = apply { this.accountNo = accountNo }
        fun accountNoReceived(accountNoReceived: String) = apply { this.accountNoReceived = accountNoReceived }
        fun amount(amount: Double) = apply { this.amount = amount }
        fun build() = AccountHistory(accountNo, accountNoReceived, amount)

        override fun toString() =
            "AccountHistory.Builder(accountNo=$accountNo, accountNoReceived=$accountNoReceived, amount=$amount)"
    }
}
