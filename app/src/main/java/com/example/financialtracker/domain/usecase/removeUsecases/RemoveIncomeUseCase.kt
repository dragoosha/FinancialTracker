package com.example.financialtracker.domain.usecase.removeUsecases

import com.example.financialtracker.domain.repository.FinancialRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RemoveIncomeUseCase {
    suspend fun execute(id: Int)
}

class RemoveIncomeUseCaseImpl @Inject constructor(private val financialRepository: FinancialRepository)
    : RemoveIncomeUseCase{
    override suspend fun execute(id: Int) {
        withContext(Dispatchers.IO) {
            financialRepository.removeAllIncome(id)
        }
    }

}