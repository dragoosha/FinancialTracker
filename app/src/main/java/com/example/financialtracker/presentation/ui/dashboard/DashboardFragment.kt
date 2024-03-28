package com.example.financialtracker.presentation.ui.dashboard

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.financialtracker.R
import com.example.financialtracker.databinding.FragmentDashboardBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    private val binding get() = _binding!!
    private val viewModel: DashboardViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        viewModel.incomeSum.observe(viewLifecycleOwner) {
            binding.incomeSumTextView.text = it
        }

        viewModel.accountsSum.observe(viewLifecycleOwner) {
            binding.accountsSumTextView.text = it
        }

        viewModel.expensesSum.observe(viewLifecycleOwner) {
            binding.expensesSumTextView.text = it
        }

        binding.salaryContainerImageView.setOnClickListener {
            val navOptions = NavOptions.Builder()
                .setPopUpTo(R.id.navigation_dashboard, true)
                .build()
            navController.navigate(R.id.salaryFragment, null, navOptions)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}

