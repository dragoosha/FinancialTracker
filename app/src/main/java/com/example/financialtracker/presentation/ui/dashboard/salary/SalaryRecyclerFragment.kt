package com.example.financialtracker.presentation.ui.dashboard.salary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financialtracker.databinding.FragmentRecyclerviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SalaryRecyclerFragment : Fragment(), SalaryListener {

    private lateinit var binding: FragmentRecyclerviewBinding
    private val viewModel: SalaryRecyclerViewModel by viewModels()
    private val adapter: SalaryAdapter = SalaryAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentRecyclerView.adapter = adapter
        binding.fragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewModel.incomeDataSalary.observe(viewLifecycleOwner) {
            adapter.incomeData = it
        }
    }

    override fun removeSalary(id: Int) {
        viewModel.remove(id)
    }
}