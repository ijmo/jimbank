package ijmo.jimbank.banking.core

import ijmo.jimbank.banking.model.BaseEntity
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Table

@Entity
@Table(name = "accounts")
class Account : BaseEntity {
    @Column(name = "account_no")
    var accountNo: String? = null

    @Column(name = "balance")
    var balance: Double? = null

    @Column(name = "owner")
    var ownerId: String? = null

    @Column(name = "enabled")
    var enabled: Boolean? = true

    @Column(name = "created_on")
    @CreatedDate
    var createdOn: LocalDateTime? = LocalDateTime.now()

    @Column(name = "modified_on")
    @LastModifiedDate
    var modifiedOn: LocalDateTime? = LocalDateTime.now()

    private constructor(accountNo: String?, balance: Double?, ownerId: String?) : this() {
        this.accountNo = accountNo
        this.balance = balance
        this.ownerId = ownerId
    }

    constructor()

    override fun toString() = "Account(accountNo=$accountNo, balance=$balance, ownerId=$ownerId)"

    data class Builder(
        private var accountNo: String? = null,
        private var balance: Double? = null,
        private var ownerId: String? = null) {

        fun accountNo(accountNo: String) = apply { this.accountNo = accountNo }
        fun balance(balance: Double) = apply { this.balance = balance }
        fun ownerId(ownerId: String) = apply { this.ownerId = ownerId }
        fun build() = Account(accountNo, balance, ownerId)

        override fun toString() = "Account.Builder(accountNo=$accountNo, balance=$balance, ownerId=$ownerId)"
    }
}