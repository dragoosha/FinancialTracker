package com.example.financialtracker.domain.usecase.calculateTotalSumUsecases

import com.example.financialtracker.domain.repository.FinancialRepository
import com.example.financialtracker.domain.utils.BaseCalculateUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface CalculateAccountsSumUsecase {
    suspend fun execute(): Flow<Int>
}

class CalculateAccountsSumUsecaseImpl @Inject constructor(private val financialRepository: FinancialRepository) :
    CalculateAccountsSumUsecase, BaseCalculateUsecase<Int>() {

    override suspend fun execute(): Flow<Int> {
        return withContext(Dispatchers.IO) {
            financialRepository.getAccounts()
                .map { accountsList ->
                    accountsList.sumOf { it.accountsSum }
                }
        }
    }
}