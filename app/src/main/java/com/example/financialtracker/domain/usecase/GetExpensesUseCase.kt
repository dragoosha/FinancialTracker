package com.example.financialtracker.domain.usecase

import com.example.financialtracker.domain.model.ExpensesModel
import com.example.financialtracker.domain.repository.FinancialRepository
import com.example.financialtracker.domain.utils.BaseUseCase
import com.example.financialtracker.domain.utils.None
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

interface GetExpensesUseCase {
    suspend fun execute(params: None): Single<List<ExpensesModel>>
    suspend fun insert(model: ExpensesModel)

}

class GetExpensesUseCaseImpl @Inject constructor(
    private val financialRepository: FinancialRepository,
) : GetExpensesUseCase, BaseUseCase<None, List<ExpensesModel>, ExpensesModel>() {
    override suspend fun execute(params: None): Single<List<ExpensesModel>> {
        return financialRepository.getExpenses()
    }

    override suspend fun insert(model: ExpensesModel) {
        financialRepository.insertExpenses(model)
    }
}