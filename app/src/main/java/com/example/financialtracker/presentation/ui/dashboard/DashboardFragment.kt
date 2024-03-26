package com.example.financialtracker.presentation.ui.dashboard

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SimpleAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import com.example.financialtracker.R
import com.example.financialtracker.databinding.ContainerAccountsBinding
import com.example.financialtracker.databinding.ContainerExpensesBinding
import com.example.financialtracker.databinding.ContainerIncomeBinding
import com.example.financialtracker.databinding.FragmentDashboardBinding
import com.example.financialtracker.presentation.ui.dashboard.DashboardViewModel.Companion.accountsTitleList
import com.example.financialtracker.presentation.ui.dashboard.DashboardViewModel.Companion.expensesTitleList
import com.example.financialtracker.presentation.ui.dashboard.DashboardViewModel.Companion.incomeTitleList
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.Date

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private var _containerIncomeBinding: ContainerIncomeBinding? = null
    private var _containerAccountsBinding: ContainerAccountsBinding? = null
    private var _containerExpensesBinding: ContainerExpensesBinding? = null
    private val binding get() = _binding!!

    private val containerIncomeBinding get() = _containerIncomeBinding
    private val containerAccountsBinding get() = _containerAccountsBinding
    private val containerExpensesBinding get() = _containerExpensesBinding

    private val viewModel: DashboardViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        _containerIncomeBinding = ContainerIncomeBinding.bind(binding.root)
        _containerExpensesBinding = ContainerExpensesBinding.bind(binding.root)
        _containerAccountsBinding = ContainerAccountsBinding.bind(binding.root)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        containerIncomeBinding?.addIncomeSum?.setOnClickListener { addIncomeButtonPressed() }


        lifecycleScope.launch {
            binding.textViewIncomeSum.text = viewModel.getIncomeSumParam().toString()
        }


        viewModel.incomeData.observe(viewLifecycleOwner) {incomeList ->
            incomeList.forEach {
                if (it.incomeCategory == "Salary") {
                    containerIncomeBinding?.textViewSalarySum?.text = it.incomeSum.toString()
                }
                if (it.incomeCategory == "Additional") {
                    containerIncomeBinding?.sumAdditional?.text = it.incomeSum.toString()
                }
            }
        }

        setUpSpinner()
    }

    private fun addIncomeButtonPressed() {
        val addSum = containerIncomeBinding?.editTextIncomeSum?.text.toString().toInt()

        containerIncomeBinding?.spinnerIncome?.onItemSelectedListener = viewModel.onTitleIncomeSelectedListener

        val date = Date().toString()

        viewModel.insertIncomeData(addSum, date)

    }

    private fun setUpSpinner() {
        containerIncomeBinding?.spinnerIncome?.adapter = ArrayAdapter (
            requireContext(),
            android.R.layout.simple_spinner_item,
            incomeTitleList
        )

        containerAccountsBinding?.spinnerAccounts?.adapter = ArrayAdapter (
            requireContext(),
            android.R.layout.simple_spinner_item,
            accountsTitleList
        )
        containerAccountsBinding?.spinnerAccounts?.onItemSelectedListener = viewModel.onTitleExpensesSelectedListener

        containerExpensesBinding?.spinnerExpenses?.adapter = ArrayAdapter (
            requireContext(),
            android.R.layout.simple_spinner_item,
            expensesTitleList
        )
        containerExpensesBinding?.spinnerExpenses?.onItemSelectedListener = viewModel.onTitleAccountsSelectedListener

    }

    override fun onResume() {
        super.onResume()

        viewModel.incomeData.observe(viewLifecycleOwner) {incomeList ->
            val firstItem = incomeList.firstOrNull()

            Log.d("INCOME_LIST1", "$firstItem")
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}

