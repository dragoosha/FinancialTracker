package com.example.financialtracker.domain.repository

import com.example.financialtracker.domain.model.AccountsModel
import com.example.financialtracker.domain.model.ExpensesModel
import com.example.financialtracker.domain.model.IncomeModel
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

interface FinancialRepository {

    suspend fun getIncome(): Single<List<IncomeModel>>
    suspend fun getAccounts(): Single<List<AccountsModel>>
    suspend fun getExpenses(): Single<List<ExpensesModel>>

    suspend fun insertIncome(incomeModel: IncomeModel)
    suspend fun insertAccounts(accountsModel: AccountsModel)
    suspend fun insertExpenses(expensesModel: ExpensesModel)
}