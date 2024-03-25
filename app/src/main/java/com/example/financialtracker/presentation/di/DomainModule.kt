package com.example.financialtracker.presentation.di


import com.example.financialtracker.domain.usecase.GetAccountsUseCase
import com.example.financialtracker.domain.usecase.GetAccountsUseCaseImpl
import com.example.financialtracker.domain.usecase.GetExpensesUseCase
import com.example.financialtracker.domain.usecase.GetExpensesUseCaseImpl
import com.example.financialtracker.domain.usecase.GetIncomeUseCase
import com.example.financialtracker.domain.usecase.GetIncomeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindGetAccountsUseCase(getAccountsUseCaseImpl: GetAccountsUseCaseImpl): GetAccountsUseCase

    @Binds
    abstract fun bindGetExpensesUseCase(getExpensesUseCaseImpl: GetExpensesUseCaseImpl): GetExpensesUseCase

    @Binds
    abstract fun bindGetIncomeUseCase(getIncomeUseCaseImpl: GetIncomeUseCaseImpl): GetIncomeUseCase
}