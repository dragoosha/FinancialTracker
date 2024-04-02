package com.example.financialtracker.presentation.ui.dashboard.accounts.moneybox

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.financialtracker.databinding.FragmentMoneyboxBinding
import com.example.financialtracker.presentation.ui.dashboard.accounts.utils.AccountsViewModel

class MoneyboxFragment : Fragment() {

    private lateinit var binding: FragmentMoneyboxBinding
    private val viewModel: AccountsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMoneyboxBinding.inflate(inflater, container, false)
        return binding.root
    }



}