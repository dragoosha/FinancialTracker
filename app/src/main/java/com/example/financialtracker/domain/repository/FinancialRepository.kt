package com.example.financialtracker.domain.repository

import com.example.financialtracker.domain.model.AccountsModel
import com.example.financialtracker.domain.model.ExpensesModel
import com.example.financialtracker.domain.model.IncomeModel
import kotlinx.coroutines.flow.Flow

interface FinancialRepository {

    suspend fun getIncome(): Flow<List<IncomeModel>>
    suspend fun getAccounts(): Flow<List<AccountsModel>>
    suspend fun getExpenses(): Flow<List<ExpensesModel>>

    suspend fun insertIncome(incomeModel: IncomeModel)
    suspend fun insertAccounts(accountsModel: AccountsModel)
    suspend fun insertExpenses(expensesModel: ExpensesModel)

    fun removeIncome(id: Int)
    fun removeAccounts(id: Int)
}