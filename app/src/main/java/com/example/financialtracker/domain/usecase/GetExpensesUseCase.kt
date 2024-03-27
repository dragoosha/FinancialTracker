package com.example.financialtracker.domain.usecase

import com.example.financialtracker.domain.model.ExpensesModel
import com.example.financialtracker.domain.repository.FinancialRepository
import com.example.financialtracker.domain.utils.BaseUseCase
import com.example.financialtracker.domain.utils.None
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetExpensesUseCase {
    suspend fun execute(params: None): Flow<List<ExpensesModel>>
    suspend fun insert(model: ExpensesModel)

    suspend fun calculateExpensesSum(): Flow<Int>

}

class GetExpensesUseCaseImpl @Inject constructor(
    private val financialRepository: FinancialRepository,
) : GetExpensesUseCase, BaseUseCase<None, List<ExpensesModel>, ExpensesModel>() {
    override suspend fun execute(params: None): Flow<List<ExpensesModel>> = flow {
        financialRepository.getExpenses()
    }

    override suspend fun insert(model: ExpensesModel) {
        financialRepository.insertExpenses(model)
    }

    override suspend fun calculateExpensesSum(): Flow<Int> {
        return withContext(Dispatchers.IO) {
            financialRepository.getExpenses()
                .map { expensesList ->
                    expensesList.sumOf {
                        it.expensesSum
                    }
                }
        }
    }
}