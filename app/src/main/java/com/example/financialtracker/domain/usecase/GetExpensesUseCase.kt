package com.example.financialtracker.domain.usecase

import com.example.financialtracker.domain.model.ExpensesModel
import com.example.financialtracker.domain.repository.FinancialRepository
import com.example.financialtracker.domain.utils.BaseUseCase
import com.example.financialtracker.domain.utils.None
import javax.inject.Inject

interface GetExpensesUseCase {
    suspend fun execute(params: None): List<ExpensesModel>
    suspend fun insert(model: ExpensesModel)

}

class GetExpensesUseCaseImpl @Inject constructor(
    private val financialRepository: FinancialRepository,
) : GetExpensesUseCase, BaseUseCase<List<ExpensesModel>, None, ExpensesModel>() {
    override suspend fun execute(params: None): List<ExpensesModel> {
        return financialRepository.getExpenses()
    }

    override suspend fun insert(model: ExpensesModel) {
        financialRepository.insertExpenses(model)
    }
}