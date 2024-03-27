package com.example.financialtracker.presentation.ui.dashboard

import android.util.Log
import android.view.View
import android.widget.AdapterView
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val accountsUseCase: GetAccountsUseCase,
    private val incomeUseCase: GetIncomeUseCase,
    private val expensesUseCase: GetExpensesUseCase,
) : ViewModel() {

    private val _incomeData = MutableLiveData<List<IncomeModel>>()
    val incomeData: LiveData<List<IncomeModel>> = _incomeData

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
        getAllIncomeData()
        getAllAccountsData()
        getAllExpensesData()
        setUpSums()
    }

    private fun getAllExpensesData() {
        viewModelScope.launch(Dispatchers.IO) {
            expensesUseCase.execute(None).collect {
                _expensesData.value = it
            }
//            expensesUseCase.calculateExpensesSum().collect {
//                _expensesSum.value = it.toString()
//            }
        }
    }

    private fun getAllAccountsData() {
        viewModelScope.launch {
            accountsUseCase.execute(None).collect {
                _accountsData.value = it
            }
//            accountsUseCase.calculateAccountsSum().collect {
//                _accountsSum.value = it.toString()
//            }
        }
    }

    private fun getAllIncomeData() {
        viewModelScope.launch(Dispatchers.Main) {
            incomeUseCase.execute(None).collect {
                _incomeData.value = it
            }
        }
    }

    private fun setUpSums() {
        viewModelScope.launch (Dispatchers.Main) {
            incomeUseCase.calculateIncomeSum().collect {
                _incomeSum.value = it.toString()
                Log.d("ThreadsViewModel", Thread.currentThread().toString())
            }
            accountsUseCase.calculateAccountsSum().collect {
                _accountsSum.value = it.toString()
            }
            expensesUseCase.calculateExpensesSum().collect {
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