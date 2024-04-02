package com.example.financialtracker.domain.usecase.getUsecases

import com.example.financialtracker.domain.model.IncomeModel
import com.example.financialtracker.domain.repository.FinancialRepository
import com.example.financialtracker.domain.utils.BaseUseCase
import com.example.financialtracker.domain.utils.None
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface GetIncomeUseCase {
    suspend fun execute(params: None): Flow<List<IncomeModel>>
    suspend fun insert(model: IncomeModel)


}

class GetIncomeUseCaseImpl @Inject constructor(private val financialRepository: FinancialRepository) :
    GetIncomeUseCase, BaseUseCase<None, List<IncomeModel>, IncomeModel>() {
    override suspend fun execute(params: None): Flow<List<IncomeModel>> {
        return withContext(Dispatchers.IO){
            return@withContext financialRepository.getIncome()
        }
    }

    override suspend fun insert(model: IncomeModel) {
        financialRepository.insertIncome(model)
    }

}

