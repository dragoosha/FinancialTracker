package com.example.financialtracker.presentation.ui.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialtracker.domain.model.AccountsModel
import com.example.financialtracker.domain.model.ExpensesModel
import com.example.financialtracker.domain.model.IncomeModel
import com.example.financialtracker.domain.usecase.calculateTotalSumUsecases.CalculateAccountsSumUsecase
import com.example.financialtracker.domain.usecase.calculateTotalSumUsecases.CalculateExpensesUseCase
import com.example.financialtracker.domain.usecase.calculateTotalSumUsecases.CalculateIncomeSumUseCase
import com.example.financialtracker.domain.usecase.getUsecases.GetAccountsUseCase
import com.example.financialtracker.domain.usecase.getUsecases.GetExpensesUseCase
import com.example.financialtracker.domain.usecase.getUsecases.GetIncomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val accountsUseCase: CalculateAccountsSumUsecase,
    private val incomeUseCase: CalculateIncomeSumUseCase,
    private val expensesUseCase: CalculateExpensesUseCase,
) : ViewModel() {


    private val _incomeSum = MutableLiveData<String>()
    val incomeSum: LiveData<String> = _incomeSum

    private val _accountsData = MutableLiveData<List<AccountsModel>>()
    val accountsData: LiveData<List<AccountsModel>> = _accountsData

    private val _accountsSum = MutableLiveData<String>()
    val accountsSum: LiveData<String> = _accountsSum


    private val _expensesData = MutableLiveData<List<ExpensesModel>>()
    val expensesData: LiveData<List<ExpensesModel>> = _expensesData

    private val _expensesSum = MutableLiveData<String>()
    val expensesSum: LiveData<String> = _expensesSum

    init {
        setUpIncomeSum()
        setUpAccountsSum()
        setUpExpensesSum()
    }

    private fun setUpExpensesSum() {
        viewModelScope.launch(Dispatchers.Main) {
            expensesUseCase.execute().collect {
                _expensesSum.value = it.toString()
            }
        }
    }

    private fun setUpAccountsSum() {
        viewModelScope.launch(Dispatchers.Main) {
            accountsUseCase.execute().collect {
                _accountsSum.value = it.toString()
            }
        }
    }

    private fun setUpIncomeSum() {
        viewModelScope.launch(Dispatchers.Main) {
            incomeUseCase.execute().collect {
                _incomeSum.value = it.toString()
            }
        }
    }
}
