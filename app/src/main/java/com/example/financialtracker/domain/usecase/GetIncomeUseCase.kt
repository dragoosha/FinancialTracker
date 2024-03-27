package com.example.financialtracker.domain.usecase

import android.util.Log
import com.example.financialtracker.domain.model.IncomeModel
import com.example.financialtracker.domain.repository.FinancialRepository
import com.example.financialtracker.domain.utils.BaseUseCase
import com.example.financialtracker.domain.utils.None
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface GetIncomeUseCase {
    suspend fun execute(params: None): Flow<List<IncomeModel>>
    suspend fun insert(model: IncomeModel)

    suspend fun calculateIncomeSum(): Flow<Int>

    fun removeAllIncome()

}

class GetIncomeUseCaseImpl @Inject constructor(private val financialRepository: FinancialRepository) :
    GetIncomeUseCase, BaseUseCase<None, List<IncomeModel>, IncomeModel>() {
    override suspend fun execute(params: None): Flow<List<IncomeModel>> = flow {
        financialRepository.getIncome()
    }

    override suspend fun insert(model: IncomeModel) {
        financialRepository.insertIncome(model)
    }

    override suspend fun calculateIncomeSum(): Flow<Int> {
        return withContext(Dispatchers.IO) {
            financialRepository.getIncome()
                .map { incomeList ->
                    incomeList.sumOf {
                        it.incomeSum
                    }
                }
        }
    }

    override fun removeAllIncome() {
        financialRepository.removeAllIncome()
    }

}

