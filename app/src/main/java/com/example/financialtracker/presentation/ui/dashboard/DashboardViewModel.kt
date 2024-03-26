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
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.exp


@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val accountsUseCase: GetAccountsUseCase,
    private val incomeUseCase: GetIncomeUseCase,
    private val expensesUseCase: GetExpensesUseCase,
) : ViewModel() {

    private var currentTitleIncome: String = "TITLE"
    private var currentTitleAccounts: String = "TITLE"
    private var currentTitleExpenses: String = "TITLE"
    private var incomeSum: Int = 0

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

    suspend fun getIncomeSumParam() : Int {
        getIncomeSum()
        Log.d("RxData", "$incomeSum")
        return incomeSum
    }

    private fun getAllExpensesData() {
        viewModelScope.launch(Dispatchers.Main) {
//            val expensesData = expensesUseCase.execute(None)
//            _expensesData.value = expensesData
            expensesUseCase.execute(None)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe{
                    expensesData -> _expensesData.value = expensesData
                }
        }
    }

    private fun getAllAccountsData() {
        viewModelScope.launch(Dispatchers.Main) {
//            val accountsData = accountsUseCase.execute(None)
//            _accountsData.value = accountsData
            accountsUseCase.execute(None)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    accountsData -> _accountsData.value = accountsData
                }
        }
    }

    private fun getAllIncomeData() {
        viewModelScope.launch {
            incomeUseCase.execute(None)
                .subscribeOn(Schedulers.io()) // Выполнение запроса в фоновом потоке
                .observeOn(AndroidSchedulers.mainThread()) // Переключение на основной поток для обновления LiveData
                .subscribe({ incomeData ->
                    _incomeData.value = incomeData
                }, { error ->
                    // Обработка ошибок при загрузке данных
                })
        }
    }

    fun insertIncomeData(sum: Int, date: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val newIncomeModel = IncomeModel(
                    0,
                    currentTitleIncome,
                    sum,
                    date
                )
                incomeUseCase.insert(newIncomeModel)
            }
        }
    }

    private suspend fun getIncomeSum() {
        viewModelScope.launch {
            incomeUseCase.calculateTotalIncome()
                .subscribeOn(Schedulers.io())
                .subscribe{
                    incomeRxSum -> incomeSum = incomeRxSum
                    Log.d("RxData", "$incomeSum")
                }
        }
    }

    val onTitleIncomeSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            currentTitleIncome = incomeTitleList[position]
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}

    }

    val onTitleExpensesSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            currentTitleExpenses = expensesTitleList[position]
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}

    }

    val onTitleAccountsSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            currentTitleAccounts = accountsTitleList[position]
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    companion object {

        val incomeTitleList = listOf(
            "Salary",
            "Additional"
        )
        val accountsTitleList = listOf(
            "Cash",
            "Card"
        )
        val expensesTitleList = listOf(
            "Groceries",
            "Transport",
            "Entertainment",
            "Food",
            "House",
            "Others"
        )

    }
}

//private fun setUpSpinners() {
//    binding.difficultySpinner.adapter = SimpleAdapter(
//        this,
//        difficultyCases,
//        android.R.layout.simple_list_item_1,
//        arrayOf(TITLE_DIFFICULTY),
//        intArrayOf(android.R.id.text1)
//    )
//    binding.difficultySpinner.onItemSelectedListener = viewModel.onDifficultyLevelSelectedListener
//
//    binding.resultSpinner.adapter = SimpleAdapter(
//        this,
//        resultCases,
//        android.R.layout.simple_list_item_1,
//        arrayOf(TITLE_RESULT),
//        intArrayOf(android.R.id.text1)
//    )
//    binding.resultSpinner.onItemSelectedListener = viewModel.onResultSelectedListener
//}