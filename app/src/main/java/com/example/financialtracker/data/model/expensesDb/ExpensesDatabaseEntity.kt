package com.example.financialtracker.data.model.expensesDb

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.financialtracker.domain.model.ExpensesModel

@Entity(
    tableName = "expenses_database"
)
data class ExpensesDatabaseEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "expenses_category", defaultValue = "") val expensesCategory: String,
    @ColumnInfo(name = "expenses_sum", defaultValue = "0") val expensesSum: Int,
    @ColumnInfo(name = "expenses_date", defaultValue = "00.00.00") val expensesDate: String
) {
    companion object {
        fun from(expensesModel: ExpensesModel): ExpensesDatabaseEntity {
            return ExpensesDatabaseEntity(
                expensesModel.id,
                expensesModel.expensesCategory,
                expensesModel.expensesSum,
                expensesModel.expensesDate
            )
        }
    }

    fun toExpensesModel(): ExpensesModel {
        return ExpensesModel(
            id,
            expensesCategory,
            expensesSum,
            expensesDate
        )
    }
}
