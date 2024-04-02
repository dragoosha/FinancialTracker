package com.example.financialtracker.domain.usecase.getUsecases

import com.example.financialtracker.domain.model.AccountsModel
import com.example.financialtracker.domain.repository.FinancialRepository
import com.example.financialtracker.domain.utils.BaseUseCase
import com.example.financialtracker.domain.utils.None
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


interface GetAccountsUseCase {
    suspend fun execute(params: None): Flow<List<AccountsModel>>
    suspend fun insert(accountsModel: AccountsModel)
}

class GetAccountsUseCaseImpl @Inject constructor(private val financialRepository: FinancialRepository) :
    GetAccountsUseCase, BaseUseCase<None, List<AccountsModel>, AccountsModel>() {
    override suspend fun execute(params: None): Flow<List<AccountsModel>> {
        return withContext(Dispatchers.IO){
            return@withContext financialRepository.getAccounts()
        }
    }

    override suspend fun insert(model: AccountsModel) {
        financialRepository.insertAccounts(model)
    }


}