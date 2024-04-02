package com.example.financialtracker.presentation.ui.dashboard.accounts.card

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.financialtracker.R
import com.example.financialtracker.databinding.FragmentCardBinding
import com.example.financialtracker.presentation.ui.dashboard.accounts.utils.AccountsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardFragment : Fragment() {

    private lateinit var binding: FragmentCardBinding
    private val viewModel: AccountsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentCardBinding.inflate(inflater, container, false)
        return binding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        requireActivity().onBackPressedDispatcher
            .addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            })

        savedInstanceState?.let {
            val fragment = CardRecyclerViewFragment()
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_recyclerView, fragment)
                .commit()
        }

        viewModel.accountsTotalSum.observe(viewLifecycleOwner) {
            binding.cardSumFragmentTextView.text = it
        }

        binding.addCardFragmentImageView.setOnClickListener {
            viewModel.addCardImageClicked(binding.cardFragmentEditText.text)
        }
    }
}