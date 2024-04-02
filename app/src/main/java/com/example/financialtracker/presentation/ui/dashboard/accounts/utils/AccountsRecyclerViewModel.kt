package com.example.financialtracker.presentation.ui.dashboard.accounts.utils


import android.app.WallpaperInfo
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.financialtracker.domain.model.AccountsModel
import com.example.financialtracker.domain.usecase.calculateTotalSumUsecases.CalculateAccountsSumUsecase
import com.example.financialtracker.domain.usecase.getUsecases.GetAccountsUseCase
import com.example.financialtracker.domain.usecase.removeUsecases.RemoveAccountsUseCase
import com.example.financialtracker.domain.utils.None
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountsRecyclerViewModel @Inject constructor(
    private val accountsRemoveUsecase: RemoveAccountsUseCase,
    private val accountsUsecase: GetAccountsUseCase
): ViewModel() {
    private val _accountsCardData = MutableLiveData<List<AccountsModel>>()
    val accountsCardData : LiveData<List<AccountsModel>> = _accountsCardData

    private val _accountsCashData = MutableLiveData<List<AccountsModel>>()
    val accountsCashData: LiveData<List<AccountsModel>> = _accountsCashData

    private val _accountsMoneyboxData = MutableLiveData<List<AccountsModel>>()
    val accountsMoneyboxData: LiveData<List<AccountsModel>> = _accountsMoneyboxData


    init {
        getAccountsCardData()
        getAccountsCashData()
        getAccountsMoneyboxData()
    }

    private fun getAccountsMoneyboxData() {
        viewModelScope.launch {
            accountsUsecase.execute(None).collect {
                val tmplist : MutableList<AccountsModel> = mutableListOf()
                it.forEach {
                    Log.d("AccountsData", "$it")
                    if (it.accountsCategory == "MoneyBox")
                        tmplist.add(it)
                }
                _accountsMoneyboxData.value = tmplist.toList()
            }
        }
    }

    private fun getAccountsCashData() {
        Log.d("AccountsData", "I was involked")
        viewModelScope.launch {
            accountsUsecase.execute(None).collect {
                val tmplist : MutableList<AccountsModel> = mutableListOf()
                it.forEach {
                    if (it.accountsCategory == "Cash")
                        tmplist.add(it)
                }
                _accountsMoneyboxData.value = tmplist.toList()
            }
        }
    }

    private fun getAccountsCardData() {
        viewModelScope.launch{
            accountsUsecase.execute(None).collect {
                val tmplist : MutableList<AccountsModel> = mutableListOf()
                it.forEach {
                    if (it.accountsCategory == "Card")
                        tmplist.add(it)
                }
                _accountsMoneyboxData.value = tmplist.toList()
            }
        }
    }

    fun removeAccount(id: Int) {
        viewModelScope.launch {
            accountsRemoveUsecase.execute(id)
        }
    }
}