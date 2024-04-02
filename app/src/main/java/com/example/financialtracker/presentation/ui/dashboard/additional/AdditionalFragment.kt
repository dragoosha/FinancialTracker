package com.example.financialtracker.presentation.ui.dashboard.additional

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.financialtracker.R
import com.example.financialtracker.databinding.FragmentAdditionalBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AdditionalFragment : Fragment() {

    private lateinit var binding: FragmentAdditionalBinding
    private val viewModel: AdditionalViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAdditionalBinding.inflate(inflater, container, false)
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
            val fragment = AdditionalRecyclerFragment()
            childFragmentManager.beginTransaction()
                .replace(R.id.fragment_recyclerView, fragment)
                .commit()
        }

        viewModel.incomeSum.observe(viewLifecycleOwner){
            binding.additionalSalarySumTextView.text = it
        }

        binding.addAdditionalFragmentImageView.setOnClickListener {
            viewModel.addSalaryFragmentImageClicked(binding.additionalFragmentEditText.text)
        }

    }
}