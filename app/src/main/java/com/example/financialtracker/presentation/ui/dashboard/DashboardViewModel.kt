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
        setUpSums()
    }

    private fun setUpSums() {
        viewModelScope.launch (Dispatchers.Main) {
            incomeUseCase.execute().collect {
                _incomeSum.value = it.toString()
            }
            accountsUseCase.execute().collect {
                _accountsSum.value = it.toString()
            }
            expensesUseCase.execute().collect {
                _expensesSum.value = it.toString()
            }
        }
    }

//    fun insertIncomeData(sum: Int, date: String) {
//        viewModelScope.launch {
//            withContext(Dispatchers.IO) {
//                val newIncomeModel = IncomeModel(
//                    0,
//                    currentTitleIncome,
//                    sum,
//                    date
//                )
//                incomeUseCase.insert(newIncomeModel)
//            }
//        }
//    }
}

//    private fun getAllExpensesData() {
//        viewModelScope.launch(Dispatchers.IO) {
//            expensesUseCase.execute(None).collect {
//                _expensesData.value = it
//            }
////            expensesUseCase.calculateExpensesSum().collect {
////                _expensesSum.value = it.toString()
////            }
//        }
//    }
//
//    private fun getAllAccountsData() {
//        viewModelScope.launch {
//            accountsUseCase.execute(None).collect {
//                _accountsData.value = it
//            }
////            accountsUseCase.calculateAccountsSum().collect {
////                _accountsSum.value = it.toString()
////            }
//        }
//    }
//
