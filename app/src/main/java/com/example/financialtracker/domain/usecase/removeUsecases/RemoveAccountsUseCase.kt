package com.example.financialtracker.domain.usecase.removeUsecases

import com.example.financialtracker.domain.repository.FinancialRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
interface RemoveAccountsUseCase {
    suspend fun execute(id: Int)
}

class RemoveAccountsUseCaseImpl @Inject constructor(private val financialRepository: FinancialRepository)
    : RemoveAccountsUseCase {
    override suspend fun execute(id: Int) {
        withContext(Dispatchers.IO) {
            financialRepository.removeAccounts(id)
        }
    }

}