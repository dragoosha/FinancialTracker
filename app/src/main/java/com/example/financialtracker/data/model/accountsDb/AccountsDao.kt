package com.example.financialtracker.data.model.accountsDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

import io.reactivex.rxjava3.core.Single

@Dao
interface AccountsDao {
    @Insert(entity = AccountsDatabaseEntity::class)
    fun insertNewAccountsData(accountsData: AccountsDatabaseEntity)

    @Query("SELECT accounts_database.id, accounts_category, accounts_sum, accounts_date FROM accounts_database")
    fun getAccountsData(): Flow<List<AccountsDatabaseEntity>>

    @Query("DELETE FROM accounts_database WHERE id = :accountId")
    fun removeAccountsById(accountId : Int)
}