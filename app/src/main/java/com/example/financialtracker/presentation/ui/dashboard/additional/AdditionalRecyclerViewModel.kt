package com.example.financialtracker.presentation.ui.dashboard.additional

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
class AdditionalRecyclerViewModel @Inject constructor(
    private val incomeRemoveUseCase: RemoveIncomeUseCase,
    private val incomeUseCase: GetIncomeUseCase
) : ViewModel() {

    private val _incomeDataAdditional = MutableLiveData<List<IncomeModel>>()
    val incomeDataAdditional: LiveData<List<IncomeModel>> = _incomeDataAdditional

    init {
        getIncomeDataAdditional()
    }
    fun remove(id : Int) {
        viewModelScope.launch {
            incomeRemoveUseCase.execute(id)
        }
    }

    private fun getIncomeDataAdditional() {
        viewModelScope.launch {
            incomeUseCase.execute(None).collect {
                val tmpList: MutableList<IncomeModel> = mutableListOf()
                it.forEach {
                    if (it.incomeCategory == "Additional") {
                        tmpList.add(it)
                    }
                }
                _incomeDataAdditional.value = tmpList.toList()
            }
        }
    }
}