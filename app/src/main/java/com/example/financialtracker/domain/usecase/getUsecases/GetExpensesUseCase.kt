package com.example.financialtracker.domain.usecase.getUsecases

import com.example.financialtracker.domain.model.ExpensesModel
import com.example.financialtracker.domain.repository.FinancialRepository
import com.example.financialtracker.domain.utils.BaseUseCase
import com.example.financialtracker.domain.utils.None
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetExpensesUseCase {
    suspend fun execute(params: None): Flow<List<ExpensesModel>>
    suspend fun insert(model: ExpensesModel)


}

class GetExpensesUseCaseImpl @Inject constructor(
    private val financialRepository: FinancialRepository,
) : GetExpensesUseCase, BaseUseCase<None, List<ExpensesModel>, ExpensesModel>() {
    override suspend fun execute(params: None): Flow<List<ExpensesModel>> {
        return withContext(Dispatchers.IO){
            return@withContext financialRepository.getExpenses()
        }
    }

    override suspend fun insert(model: ExpensesModel) {
        financialRepository.insertExpenses(model)
    }

}