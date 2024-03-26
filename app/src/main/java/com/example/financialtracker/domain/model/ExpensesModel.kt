package com.example.financialtracker.domain.model

import java.util.Date

data class ExpensesModel(
    val id: Int = 0,
    val expensesCategory: String,
    val expensesSum: Int,
    val expensesDate: String
)