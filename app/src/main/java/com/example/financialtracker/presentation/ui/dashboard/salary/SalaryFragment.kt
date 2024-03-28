package com.example.financialtracker.presentation.ui.dashboard.salary

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.financialtracker.R
import com.example.financialtracker.databinding.FragmentSalaryBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SalaryFragment : Fragment() {

    private lateinit var binding: FragmentSalaryBinding
    private val viewModel: SalaryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSalaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            val fragment = SalaryRecyclerFragment()
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_salary_recyclerView, fragment)
                .commit()
        }

        viewModel.incomeSum.observe(viewLifecycleOwner){
            binding.salarySumFragmentTextView.text = it
        }

        binding.addSalaryFragmentImageView.setOnClickListener {
            viewModel.addSalaryFragmentImageClicked(binding.addSalaryFragmentEditText.text)
        }

    }
}