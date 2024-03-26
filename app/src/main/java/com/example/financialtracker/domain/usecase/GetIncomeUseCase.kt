package com.example.financialtracker.domain.usecase

import com.example.financialtracker.domain.model.IncomeModel
import com.example.financialtracker.domain.repository.FinancialRepository
import com.example.financialtracker.domain.utils.BaseUseCase
import com.example.financialtracker.domain.utils.None
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject


interface GetIncomeUseCase {
    suspend fun execute(params: None): Single<List<IncomeModel>>
    suspend fun insert(model: IncomeModel)

    suspend fun calculateTotalIncome() : Single<Int>
}

class GetIncomeUseCaseImpl @Inject constructor(private val financialRepository: FinancialRepository) :
    GetIncomeUseCase, BaseUseCase<None, List<IncomeModel>, IncomeModel>() {
    override suspend fun execute(params: None): Single<List<IncomeModel>>{
        return financialRepository.getIncome()
    }

    override suspend fun insert(model: IncomeModel) {
        financialRepository.insertIncome(model)
    }

    override suspend fun calculateTotalIncome(): Single<Int> {
        return execute(None)
            .subscribeOn(Schedulers.io())
            .map { incomeList ->
                var totalSum = 0
                incomeList.forEach { income ->
                    totalSum += income.incomeSum
                }
                totalSum
            }
    }
}