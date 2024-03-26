package com.example.financialtracker.domain.model

import java.util.Date

class IncomeModel(
    val id: Int = 0,
    val incomeCategory: String,
    val incomeSum: Int,
    val incomeDate: String
)
{
    override fun toString(): String {
        return "$id, $incomeCategory, $incomeSum, $incomeDate"
    }
}