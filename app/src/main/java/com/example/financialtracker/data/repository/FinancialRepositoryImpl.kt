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
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class FinancialRepositoryImpl @Inject constructor(
    private val incomeDao: IncomeDao,
    private val accountsDao: AccountsDao,
    private val expensesDao: ExpensesDao
) : FinancialRepository {

    override suspend fun getIncome(): Flow<List<IncomeModel>> {
        return withContext(Dispatchers.IO) {
            return@withContext incomeDao.getIncomeData()
                .map { incomeDatabaseEntity -> incomeDatabaseEntity.map {
                    it.toIncomeModel() }
                }
        }
    }

    override suspend fun getAccounts(): Flow<List<AccountsModel>> {
        return withContext(Dispatchers.IO) {
            return@withContext accountsDao.getAccountsData()
                .map { accountsDatabaseEntity -> accountsDatabaseEntity.map {
                    it.toAccountsModel() }
                }
        }
    }


    override suspend fun getExpenses(): Flow<List<ExpensesModel>> {
        return withContext(Dispatchers.IO) {
            return@withContext expensesDao.getExpensesData()
                .map { expensesDatabaseEntity -> expensesDatabaseEntity.map{
                    it.toExpensesModel() }
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

    override fun removeAllIncome() {
        return incomeDao.removeAll()
    }


}