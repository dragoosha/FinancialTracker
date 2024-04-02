package com.example.financialtracker.presentation.ui.dashboard.accounts.card

import android.os.Bundle
import android.util.Log
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
class CardRecyclerViewFragment: Fragment(), Listener {

    private lateinit var binding : FragmentRecyclerviewBinding
    private val viewModel : AccountsRecyclerViewModel by viewModels()
    private val adapter: Adapter = Adapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRecyclerviewBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fragmentRecyclerView.adapter = adapter
        Log.d("AccountsDataAdapter", "i used adapter")

        binding.fragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        viewModel.accountsCardData.observe(viewLifecycleOwner) {
            Log.d("AccountsDataAdapter", "i am observing")
            adapter.accountsData = it
        }
    }


    override fun remove(id: Int) {
        TODO()
    }
}