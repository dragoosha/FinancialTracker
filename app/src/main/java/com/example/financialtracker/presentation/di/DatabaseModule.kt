package com.example.financialtracker.presentation.di

import android.app.Application
import com.example.financialtracker.data.model.accountsDb.AccountsDao
import com.example.financialtracker.data.model.accountsDb.AccountsDatabase
import com.example.financialtracker.data.model.expensesDb.ExpensesDao
import com.example.financialtracker.data.model.expensesDb.ExpensesDatabase
import com.example.financialtracker.data.model.incomeDb.IncomeDao
import com.example.financialtracker.data.model.incomeDb.IncomeDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideIncomeDatabase(context: Application): IncomeDatabase {
        return IncomeDatabase.getIncomeDatabaseInstance(context)
    }

    @Provides
    @Singleton
    fun provideIncomeDao(incomeDatabase: IncomeDatabase): IncomeDao {
        return incomeDatabase.getIncomeDao()
    }

    @Provides
    @Singleton
    fun provideAccountsDatabase(context: Application): AccountsDatabase {
        return AccountsDatabase.getAccountsDatabaseInstance(context)
    }

    @Provides
    @Singleton
    fun provideAccountsDao(accountsDatabase: AccountsDatabase): AccountsDao {
        return accountsDatabase.getAccountsDao()
    }

    @Provides
    @Singleton
    fun provideExpensesDatabase(context: Application): ExpensesDatabase {
        return ExpensesDatabase.getExpensesDatabaseInstance(context)
    }

    @Provides
    @Singleton
    fun provideExpensesDao(expensesDatabase: ExpensesDatabase): ExpensesDao {
        return expensesDatabase.getExpensesDao()
    }

}