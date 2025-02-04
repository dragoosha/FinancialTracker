package com.example.financialtracker.domain.model


data class AccountsModel(
    val id: Int = 0,
    val accountsCategory: String,
    val accountsSum: Int,
    val accountsDate: String
)
{
    override fun toString(): String {
        return "id : $id" +
                "sum: $accountsSum"
    }
}