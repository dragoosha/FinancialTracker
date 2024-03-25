package com.example.fintrack.domain.model

import java.util.Date

data class ExpensesModel(
    val id: Int,
    val expensesCategory: String,
    val expensesSum: Int,
    val expensesDate: String
)