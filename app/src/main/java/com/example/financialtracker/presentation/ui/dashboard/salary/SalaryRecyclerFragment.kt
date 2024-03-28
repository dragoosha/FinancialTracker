package com.example.financialtracker.presentation.ui.dashboard.salary

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financialtracker.databinding.FragmentSalaryBinding
import com.example.financialtracker.databinding.FragmentSalaryRecyclerviewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SalaryRecyclerFragment : Fragment(),SalaryListener {

    private lateinit var binding: FragmentSalaryRecyclerviewBinding
    private val viewModel: SalaryRecyclerViewModel by viewModels()
    private val adapter: SalaryAdapter = SalaryAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSalaryRecyclerviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentSalaryRecyclerView.adapter = adapter
        binding.fragmentSalaryRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewModel.incomeData.observe(viewLifecycleOwner) {
            adapter.data = it
        }
    }

    override fun removeNote(id: Int) {
        viewModel.remove(id)
    }
}