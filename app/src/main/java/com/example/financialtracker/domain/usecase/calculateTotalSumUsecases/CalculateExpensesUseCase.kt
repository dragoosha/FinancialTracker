package com.example.financialtracker.domain.usecase.calculateTotalSumUsecases

import com.example.financialtracker.domain.repository.FinancialRepository
import com.example.financialtracker.domain.utils.BaseCalculateUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CalculateExpensesUseCase {
    suspend fun execute(): Flow<Int>

}

class CalculateExpensesUseCaseImpl @Inject constructor(
    private val financialRepository: FinancialRepository,
) : CalculateExpensesUseCase, BaseCalculateUsecase<Int>() {

    override suspend fun execute(): Flow<Int> {
        return withContext(Dispatchers.IO) {
            financialRepository.getExpenses().map { expensesList ->
                    expensesList.sumOf {
                        it.expensesSum
                    }
                }
        }
    }
}