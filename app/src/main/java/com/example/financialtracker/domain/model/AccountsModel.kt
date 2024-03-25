package com.example.fintrack.domain.model

import java.util.Date


data class AccountsModel(
    val id: Int,
    val accountsCategory: String,
    val accountsSum: Int,
    val accountsDate: String
)