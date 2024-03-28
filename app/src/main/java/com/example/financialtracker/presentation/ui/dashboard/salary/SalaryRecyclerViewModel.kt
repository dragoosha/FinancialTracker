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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SalaryRecyclerViewModel @Inject constructor(
    private val incomeRemoveUseCase: RemoveIncomeUseCase,
    private val incomeUseCase: GetIncomeUseCase
) : ViewModel() {

    private val _incomeData = MutableLiveData<List<IncomeModel>>()
    val incomeData: LiveData<List<IncomeModel>> = _incomeData

    init {
        getIncomeData()
    }
    fun remove(id : Int) {
        viewModelScope.launch {
            incomeRemoveUseCase.execute(id)
        }
    }

    private fun getIncomeData() {
        viewModelScope.launch {
            incomeUseCase.execute(None).collect {
                val tmpList: MutableList<IncomeModel> = mutableListOf()
                it.forEach {
                    if (it.incomeCategory == "Salary")
                        tmpList.add(it)
                }
                _incomeData.value = tmpList.toList()
            }
        }
    }
}