package com.example.financialtracker.presentation.ui.dashboard.salary

import android.os.Build
import android.text.Editable
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialtracker.domain.model.IncomeModel
import com.example.financialtracker.domain.usecase.calculateTotalSumUsecases.CalculateIncomeSumUseCase
import com.example.financialtracker.domain.usecase.getUsecases.GetIncomeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class SalaryViewModel @Inject constructor(
    private val incomeSumUseCase: CalculateIncomeSumUseCase,
    private val incomeInsertUseCase: GetIncomeUseCase
) : ViewModel() {

    private val _incomeSum = MutableLiveData<String>()
    val incomeSum: LiveData<String> = _incomeSum

    init {
        setUpSum()
    }

    private fun setUpSum() {
        viewModelScope.launch(Dispatchers.Main) {
            incomeSumUseCase.execute().collect {
                _incomeSum.value = it.toString()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertIncomeData(sum: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            incomeInsertUseCase.insert(
                IncomeModel(
                    0,
                    "Salary",
                    sum,
                    (LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).toString()
                )
            )
        }
    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun addSalaryFragmentImageClicked(text: Editable) {
        if (text.toString().isNotBlank() && text.toString().isNotEmpty())
            insertIncomeData(text.toString().toInt())
        else
            TODO()
    }
}