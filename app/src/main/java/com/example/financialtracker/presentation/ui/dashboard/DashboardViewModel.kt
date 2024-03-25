package com.example.financialtracker.presentation.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialtracker.domain.model.AccountsModel
import com.example.financialtracker.domain.model.ExpensesModel
import com.example.financialtracker.domain.model.IncomeModel
import com.example.financialtracker.domain.usecase.GetAccountsUseCase
import com.example.financialtracker.domain.usecase.GetExpensesUseCase
import com.example.financialtracker.domain.usecase.GetIncomeUseCase
import com.example.financialtracker.domain.utils.None
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val accountsUseCase: GetAccountsUseCase,
    private val incomeUseCase: GetIncomeUseCase,
    private val expensesUseCase: GetExpensesUseCase,
) : ViewModel() {

    private val _incomeData = MutableLiveData<List<IncomeModel>>()
    val incomeData: LiveData<List<IncomeModel>> = _incomeData

    private val _accountsData = MutableLiveData<List<AccountsModel>>()
    val accountsData: LiveData<List<AccountsModel>> = _accountsData

    private val _expensesData = MutableLiveData<List<ExpensesModel>>()
    val expensesData: LiveData<List<ExpensesModel>> = _expensesData


    init {
        getAllIncomeData()
        getAllAccountsData()
        getAllExpensesData()
    }

    private fun getAllExpensesData() {
        viewModelScope.launch {
            val expensesData = expensesUseCase.execute(None)
            _expensesData.value = expensesData
        }
    }

    private fun getAllAccountsData() {
        viewModelScope.launch {
            val accountsData = accountsUseCase.execute(None)
            _accountsData.value = accountsData
        }
    }

    private fun getAllIncomeData() {
        viewModelScope.launch {
            val incomeData = incomeUseCase.execute(None)
            _incomeData.value = incomeData
        }
    }
}