package com.example.financialtracker.domain.usecase

import com.example.financialtracker.domain.model.IncomeModel
import com.example.financialtracker.domain.repository.FinancialRepository
import com.example.financialtracker.domain.utils.BaseUseCase
import com.example.financialtracker.domain.utils.None
import javax.inject.Inject


interface GetIncomeUseCase {
    suspend fun execute(params: None): List<IncomeModel>
    suspend fun insert(model: IncomeModel)
}

class GetIncomeUseCaseImpl @Inject constructor(private val financialRepository: FinancialRepository) :
    GetIncomeUseCase, BaseUseCase<List<IncomeModel>, None, IncomeModel>() {
    override suspend fun execute(params: None): List<IncomeModel> {
        return financialRepository.getIncome()
    }

    override suspend fun insert(model: IncomeModel) {
        financialRepository.insertIncome(model)
    }
}