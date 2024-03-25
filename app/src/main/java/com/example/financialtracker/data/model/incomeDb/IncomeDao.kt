package com.example.financialtracker.data.model.incomeDb


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.financialtracker.data.model.incomeDb.IncomeDatabaseEntity

@Dao
interface IncomeDao {
    @Insert(entity = IncomeDatabaseEntity::class)
    fun insertNewIncomeData(incomeData: IncomeDatabaseEntity)

    @Query("SELECT income_database.id, income_category, income_sum, income_date FROM income_database")
    fun getIncomeData(): List<IncomeDatabaseEntity>
}