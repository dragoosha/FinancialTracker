package com.example.financialtracker.presentation.di

import com.example.financialtracker.data.repository.FinancialRepositoryImpl
import com.example.financialtracker.domain.repository.FinancialRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindProgramRepository(programRepositoryImpl: FinancialRepositoryImpl): FinancialRepository
}