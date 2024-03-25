package com.example.financialtracker.data.model.incomeDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.financialtracker.domain.model.IncomeModel

@Entity(
    tableName = "income_database"
)
data class IncomeDatabaseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "income_category", defaultValue = "") val incomeCategory: String,
    @ColumnInfo(name = "income_sum", defaultValue = "0") val incomeSum: Int,
    @ColumnInfo(name = "income_date", defaultValue = "00.00.00") val incomeDate: String
) {

    companion object {
        fun from(incomeModel: IncomeModel): IncomeDatabaseEntity {
            return IncomeDatabaseEntity(
                incomeModel.id,
                incomeModel.incomeCategory,
                incomeModel.incomeSum,
                incomeModel.incomeDate
            )
        }
    }

    fun toIncomeModel(): IncomeModel {
        return IncomeModel(
            id,
            incomeCategory,
            incomeSum,
            incomeDate
        )
    }
}