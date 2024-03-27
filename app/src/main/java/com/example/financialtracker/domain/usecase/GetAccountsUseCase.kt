package com.example.financialtracker.domain.usecase

import com.example.financialtracker.domain.model.AccountsModel
import com.example.financialtracker.domain.model.ExpensesModel
import com.example.financialtracker.domain.repository.FinancialRepository
import com.example.financialtracker.domain.utils.BaseUseCase
import com.example.financialtracker.domain.utils.None
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface GetAccountsUseCase {
    suspend fun execute(params: None): Flow<List<AccountsModel>>
    suspend fun insert(accountsModel: AccountsModel)
    suspend fun calculateAccountsSum(): Flow<Int>
}

class GetAccountsUseCaseImpl @Inject constructor(private val financialRepository: FinancialRepository) :
    GetAccountsUseCase, BaseUseCase<None, List<AccountsModel>, AccountsModel>() {
    override suspend fun execute(params: None): Flow<List<AccountsModel>> = flow {
        financialRepository.getAccounts()
    }

    override suspend fun insert(model: AccountsModel) {
        financialRepository.insertAccounts(model)
    }

    override suspend fun calculateAccountsSum(): Flow<Int> {
        return withContext(Dispatchers.IO) {
            financialRepository.getAccounts()
                .map { accountsList ->
                accountsList.sumOf { it.accountsSum }}
        }
    }
}