package com.example.financialtracker.domain.usecase.calculateTotalSumUsecases

import com.example.financialtracker.domain.repository.FinancialRepository
import com.example.financialtracker.domain.utils.BaseCalculateUsecase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface CalculateIncomeSumUseCase {

    suspend fun execute(): Flow<Int>


}

class CalculateIncomeSumUseCaseImpl @Inject constructor(private val financialRepository: FinancialRepository) :
    CalculateIncomeSumUseCase, BaseCalculateUsecase<Int>() {

    override suspend fun execute(): Flow<Int> {
        return withContext(Dispatchers.IO) {
            financialRepository.getIncome()
                .map { incomeList ->
                    incomeList.sumOf {
                        it.incomeSum
                    }
                }
        }
    }

}

