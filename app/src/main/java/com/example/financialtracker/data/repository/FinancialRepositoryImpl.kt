package com.example.financialtracker.data.repository

import com.example.financialtracker.data.model.accountsDb.AccountsDao
import com.example.financialtracker.data.model.accountsDb.AccountsDatabaseEntity
import com.example.financialtracker.data.model.expensesDb.ExpensesDao
import com.example.financialtracker.data.model.expensesDb.ExpensesDatabaseEntity
import com.example.financialtracker.data.model.incomeDb.IncomeDao
import com.example.financialtracker.data.model.incomeDb.IncomeDatabaseEntity
import com.example.financialtracker.domain.model.AccountsModel
import com.example.financialtracker.domain.model.ExpensesModel
import com.example.financialtracker.domain.model.IncomeModel
import com.example.financialtracker.domain.repository.FinancialRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class FinancialRepositoryImpl @Inject constructor(
    private val incomeDao: IncomeDao,
    private val accountsDao: AccountsDao,
    private val expensesDao: ExpensesDao
) : FinancialRepository {

    override suspend fun getIncome(): List<IncomeModel> {
        return withContext(Dispatchers.IO) {
            return@withContext incomeDao.getIncomeData()
                .map { incomeDatabaseEntity -> incomeDatabaseEntity.toIncomeModel() }
        }
    }

    override suspend fun getAccounts(): List<AccountsModel> {
        return withContext(Dispatchers.IO) {
            return@withContext accountsDao.getAccountsData().map { accountsDatabaseEntity ->
                accountsDatabaseEntity.toAccountsModel()
            }
        }
    }

    override suspend fun getExpenses(): List<ExpensesModel> {
        return withContext(Dispatchers.IO) {
            return@withContext expensesDao.getExpensesData().map { expensesDatabaseEntity ->
                expensesDatabaseEntity.toExpensesModel()
            }
        }
    }

    override suspend fun insertIncome(incomeModel: IncomeModel) {
        incomeDao.insertNewIncomeData(IncomeDatabaseEntity.from(incomeModel))
    }

    override suspend fun insertAccounts(accountsModel: AccountsModel) {
        accountsDao.insertNewAccountsData(AccountsDatabaseEntity.from(accountsModel))
    }

    override suspend fun insertExpenses(expensesModel: ExpensesModel) {
        expensesDao.insertNewExpensesData(ExpensesDatabaseEntity.from(expensesModel))
    }
}