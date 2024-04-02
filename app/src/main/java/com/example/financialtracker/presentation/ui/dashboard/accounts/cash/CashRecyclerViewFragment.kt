package com.example.financialtracker.presentation.ui.dashboard.accounts.cash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.financialtracker.databinding.FragmentRecyclerviewBinding
import com.example.financialtracker.presentation.ui.dashboard.accounts.utils.AccountsRecyclerViewModel
import com.example.financialtracker.presentation.ui.dashboard.accounts.utils.Adapter
import com.example.financialtracker.presentation.ui.dashboard.accounts.utils.Listener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CashRecyclerViewFragment: Fragment(), Listener {

    private lateinit var binding : FragmentRecyclerviewBinding
    private val viewModel : AccountsRecyclerViewModel by viewModels()
    private val adapter: Adapter = Adapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRecyclerviewBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentRecyclerView.adapter = adapter
        binding.fragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewModel.accountsCashData.observe(viewLifecycleOwner) {
            adapter.accountsData = it
        }
    }


    override fun remove(id: Int) {

    }
}