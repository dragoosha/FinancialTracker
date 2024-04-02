package com.example.financialtracker.data.model.incomeDb


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface IncomeDao {
    @Insert(entity = IncomeDatabaseEntity::class)
    fun insertNewIncomeData(incomeData: IncomeDatabaseEntity)

    @Query("SELECT income_database.id, income_category, income_sum, income_date FROM income_database")
    fun getIncomeData(): Flow<List<IncomeDatabaseEntity>>

    @Query("DELETE FROM income_database WHERE id = :incomeId")
    fun removeIncomeById(incomeId : Int)

}