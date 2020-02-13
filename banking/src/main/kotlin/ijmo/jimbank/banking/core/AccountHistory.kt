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

    @Column(name = "message")
    var message: String? = null

    @Column(name = "note")
    var note: String? = null

    @Column(name = "clerk_id")
    var clerkId: String? = null

    @Column(name = "created_on")
    @CreatedDate
    var createdOn: LocalDateTime? = LocalDateTime.now()

    private constructor(accountNo: String?, accountNoReceived: String?, amount: Double?, message: String?, note: String?, clerkId: String?) : this() {
        this.accountNo = accountNo
        this.accountNoReceived = accountNoReceived
        this.amount = amount
        this.message = message
        this.note = note
        this.clerkId = clerkId
    }

    constructor()

    override fun toString() = "AccountHistory(accountNo=$accountNo, accountNoReceived=$accountNoReceived, amount=$amount, message=$message, note=$note, clerkId=$clerkId)"

    data class Builder(
        private var accountNo: String? = null,
        private var accountNoReceived: String? = null,
        private var amount: Double? = null,
        private var message: String? = null,
        private var note: String? = null,
        private var clerkId: String? = null) {

        fun accountNo(accountNo: String) = apply { this.accountNo = accountNo }
        fun accountNoReceived(accountNoReceived: String) = apply { this.accountNoReceived = accountNoReceived }
        fun amount(amount: Double) = apply { this.amount = amount }
        fun message(message: String) = apply { this.message = message }
        fun note(note: String) = apply { this.note = note }
        fun clerkId(clerkId: String?) = apply { this.clerkId = clerkId }
        fun build() = AccountHistory(accountNo, accountNoReceived, amount, message, note, clerkId)

        override fun toString() =
            "AccountHistory.Builder(accountNo=$accountNo, accountNoReceived=$accountNoReceived, amount=$amount, message=$message, note=$note, clerkId=$clerkId)"
    }
}
