package com.example.financialtracker.domain.usecase

import com.example.financialtracker.domain.model.AccountsModel
import com.example.financialtracker.domain.repository.FinancialRepository
import com.example.financialtracker.domain.utils.BaseUseCase
import com.example.financialtracker.domain.utils.None
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject


interface GetAccountsUseCase {
    suspend fun execute(params: None): Single<List<AccountsModel>>
    suspend fun insert(accountsModel: AccountsModel)
}

class GetAccountsUseCaseImpl @Inject constructor(private val financialRepository: FinancialRepository) :
    GetAccountsUseCase, BaseUseCase<None, List<AccountsModel>, AccountsModel>() {
    override suspend fun execute(params: None): Single<List<AccountsModel>> {
        return financialRepository.getAccounts()
    }

    override suspend fun insert(model: AccountsModel) {
        financialRepository.insertAccounts(model)
    }
}