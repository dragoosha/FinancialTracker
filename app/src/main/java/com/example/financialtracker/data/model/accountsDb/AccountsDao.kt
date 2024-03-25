package com.example.fintrack.data.model.accountsDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface AccountsDao {
    @Insert(entity = AccountsDatabaseEntity::class)
    fun insertNewAccountsData(accountsData: AccountsDatabaseEntity)

    @Query("SELECT accounts_database.id, accounts_category, accounts_sum, accounts_date FROM accounts_database")
    fun getAccountsData(): List<AccountsDatabaseEntity>

}