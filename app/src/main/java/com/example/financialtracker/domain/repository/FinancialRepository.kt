package com.example.financialtracker.domain.repository

import com.example.financialtracker.domain.model.AccountsModel
import com.example.financialtracker.domain.model.ExpensesModel
import com.example.financialtracker.domain.model.IncomeModel

interface FinancialRepository {

    suspend fun getIncome(): List<IncomeModel>
    suspend fun getAccounts(): List<AccountsModel>
    suspend fun getExpenses(): List<ExpensesModel>

    suspend fun insertIncome(incomeModel: IncomeModel)
    suspend fun insertAccounts(accountsModel: AccountsModel)
    suspend fun insertExpenses(expensesModel: ExpensesModel)
}