package com.example.fintrack.data.model.accountsDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.fintrack.domain.model.AccountsModel

@Entity(
    tableName = "accounts_database"
)
data class AccountsDatabaseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "accounts_category", defaultValue = "") val accountsCategory: String,
    @ColumnInfo(name = "accounts_sum", defaultValue = "0") val accountsSum: Int,
    @ColumnInfo(name = "accounts_date", defaultValue = "00.00.00") val accountsDate: String
) {
    companion object {
        fun from(accountsModel: AccountsModel): AccountsDatabaseEntity {
            return AccountsDatabaseEntity(
                accountsModel.id,
                accountsModel.accountsCategory,
                accountsModel.accountsSum,
                accountsModel.accountsDate
            )
        }
    }

    fun toAccountsModel(): AccountsModel {
        return AccountsModel(
            id,
            accountsCategory,
            accountsSum,
            accountsDate
        )
    }
}
