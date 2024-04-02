package com.example.financialtracker.presentation.ui.dashboard.accounts.cash

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.financialtracker.R
import com.example.financialtracker.databinding.FragmentCashBinding

class CashFragment : Fragment() {

    private lateinit var binding: FragmentCashBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCashBinding.inflate(inflater, container, false)
        return binding.root
    }

}