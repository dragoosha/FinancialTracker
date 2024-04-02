package com.example.financialtracker.presentation.di


import com.example.financialtracker.domain.usecase.calculateTotalSumUsecases.CalculateAccountsSumUsecase
import com.example.financialtracker.domain.usecase.calculateTotalSumUsecases.CalculateAccountsSumUsecaseImpl
import com.example.financialtracker.domain.usecase.calculateTotalSumUsecases.CalculateExpensesUseCase
import com.example.financialtracker.domain.usecase.calculateTotalSumUsecases.CalculateExpensesUseCaseImpl
import com.example.financialtracker.domain.usecase.calculateTotalSumUsecases.CalculateIncomeSumUseCase
import com.example.financialtracker.domain.usecase.calculateTotalSumUsecases.CalculateIncomeSumUseCaseImpl
import com.example.financialtracker.domain.usecase.getUsecases.GetAccountsUseCase
import com.example.financialtracker.domain.usecase.getUsecases.GetAccountsUseCaseImpl
import com.example.financialtracker.domain.usecase.getUsecases.GetExpensesUseCase
import com.example.financialtracker.domain.usecase.getUsecases.GetExpensesUseCaseImpl
import com.example.financialtracker.domain.usecase.getUsecases.GetIncomeUseCase
import com.example.financialtracker.domain.usecase.getUsecases.GetIncomeUseCaseImpl
import com.example.financialtracker.domain.usecase.removeUsecases.RemoveAccountsUseCase
import com.example.financialtracker.domain.usecase.removeUsecases.RemoveAccountsUseCaseImpl
import com.example.financialtracker.domain.usecase.removeUsecases.RemoveIncomeUseCase
import com.example.financialtracker.domain.usecase.removeUsecases.RemoveIncomeUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindGetAccountsUseCase(getAccountsUseCaseImpl: GetAccountsUseCaseImpl):
            GetAccountsUseCase

    @Binds
    abstract fun bindGetExpensesUseCase(getExpensesUseCaseImpl: GetExpensesUseCaseImpl):
            GetExpensesUseCase

    @Binds
    abstract fun bindGetIncomeUseCase(getIncomeUseCaseImpl: GetIncomeUseCaseImpl):
            GetIncomeUseCase

    @Binds
    abstract fun bindCalculateAccountsUseCase(calculateAccountsSumUsecaseImpl: CalculateAccountsSumUsecaseImpl):
            CalculateAccountsSumUsecase

    @Binds
    abstract fun bindCalculateExpensesUseCase(calculateExpensesUseCaseImpl: CalculateExpensesUseCaseImpl):
            CalculateExpensesUseCase

    @Binds
    abstract fun bindCalculateIncomeUseCase(calculateIncomeSumUseCaseImpl: CalculateIncomeSumUseCaseImpl):
            CalculateIncomeSumUseCase

    @Binds
    abstract fun bindRemoveIncomeUseCase(removeIncomeUseCaseImpl: RemoveIncomeUseCaseImpl):
            RemoveIncomeUseCase

    @Binds
    abstract fun bindRemoveAccountsUseCase(removeAccountsUseCaseImpl: RemoveAccountsUseCaseImpl):
            RemoveAccountsUseCase

}