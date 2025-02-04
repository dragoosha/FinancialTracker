package com.example.financialtracker.presentation.ui.dashboard.salary

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialtracker.domain.model.IncomeModel
import com.example.financialtracker.domain.usecase.getUsecases.GetIncomeUseCase
import com.example.financialtracker.domain.usecase.removeUsecases.RemoveIncomeUseCase
import com.example.financialtracker.domain.utils.None
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SalaryRecyclerViewModel @Inject constructor(
    private val incomeRemoveUseCase: RemoveIncomeUseCase,
    private val incomeUseCase: GetIncomeUseCase
) : ViewModel() {

    private val _incomeDataSalary = MutableLiveData<List<IncomeModel>>()
    val incomeDataSalary: LiveData<List<IncomeModel>> = _incomeDataSalary

    init {
        getIncomeDataSalary()
    }
    fun remove(id : Int) {
        viewModelScope.launch {
            incomeRemoveUseCase.execute(id)
        }
    }

    private fun getIncomeDataSalary() {
        viewModelScope.launch {
            incomeUseCase.execute(None).collect {
                val tmpList: MutableList<IncomeModel> = mutableListOf()
                it.forEach {
                    if (it.incomeCategory == "Salary") {
                        tmpList.add(it)
                        Log.d("Viewmodel", "$it")
                    }

                }
                _incomeDataSalary.value = tmpList.toList()
            }
        }
    }
}