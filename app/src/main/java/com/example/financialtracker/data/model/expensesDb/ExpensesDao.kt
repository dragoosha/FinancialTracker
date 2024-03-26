package com.example.financialtracker.data.model.expensesDb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.financialtracker.data.model.expensesDb.ExpensesDatabaseEntity
import io.reactivex.rxjava3.core.Single

@Dao
interface ExpensesDao {
    @Insert(entity = ExpensesDatabaseEntity::class)
    fun insertNewExpensesData(expensesData: ExpensesDatabaseEntity)

    @Query("SELECT expenses_database.id, expenses_category, expenses_sum, expenses_date FROM expenses_database")
    fun getExpensesData(): Single<List<ExpensesDatabaseEntity>>
}