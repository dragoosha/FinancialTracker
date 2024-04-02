package com.example.financialtracker.presentation.ui.dashboard.accounts.utils

import android.os.Build
import android.text.Editable
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialtracker.domain.model.AccountsModel
import com.example.financialtracker.domain.usecase.calculateTotalSumUsecases.CalculateAccountsSumUsecase
import com.example.financialtracker.domain.usecase.getUsecases.GetAccountsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AccountsViewModel @Inject constructor(
    private val accountsSumUsecase: CalculateAccountsSumUsecase,
    private val accountsInsertUseCas: GetAccountsUseCase
): ViewModel() {
    private val _accountsTotalSum = MutableLiveData<String>()
    val accountsTotalSum: LiveData<String> = _accountsTotalSum


    init {
        setUpSum()
    }

    private fun setUpSum() {
        viewModelScope.launch {
            accountsSumUsecase.execute().collect() {
                _accountsTotalSum.value = it.toString()
            }
        }
    }

    private fun insertAccountsMoneyboxData() {

    }

    private fun insertAccountsCashData() {
        TODO("Not yet implemented")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun insertAccountsCardData(sum: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            accountsInsertUseCas.insert(
                AccountsModel(
                    0,
                    "Accounts",
                    sum,
                    (LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).toString()
                )
            )
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun addCardImageClicked(text: Editable) {
        if (text.toString().isNotBlank() && text.toString().isNotEmpty())
            insertAccountsCardData(text.toString().toInt())
        else
            TODO()
    }
}