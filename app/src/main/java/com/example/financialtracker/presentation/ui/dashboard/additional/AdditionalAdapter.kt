package com.example.financialtracker.presentation.ui.dashboard.additional

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.financialtracker.databinding.RecyclerviewItemBinding
import com.example.financialtracker.domain.model.IncomeModel


class NoteDiffUtil (
    private val oldList: List<IncomeModel>,
    private val newList: List<IncomeModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {return oldList.size}

    override fun getNewListSize(): Int {return newList.size}

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem.id == newItem.id    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return oldItem == newItem    }
}

interface AdditionalListener {
    fun removeAdditional(id : Int)
}

class AdditionalAdapter (
    private val additionalListener: AdditionalListener
) :
   RecyclerView.Adapter<AdditionalAdapter.AdditionalHolder>(), View.OnClickListener{

    var incomeData : List<IncomeModel> = emptyList()
        set(newValue) {
            val diffUtil = NoteDiffUtil(field, newValue)
            val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
            field = newValue
            diffUtilResult.dispatchUpdatesTo(this@AdditionalAdapter)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdditionalHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewItemBinding.inflate(inflater, parent, false)
        binding.deleteIconItemImageView.setOnClickListener(this)


        return AdditionalHolder(binding)
    }


    override fun getItemCount(): Int {
        return incomeData.size}

    override fun onBindViewHolder(holder: AdditionalHolder, position: Int) {
        val incomeData = incomeData[position]
        with(holder.binding) {
            deleteIconItemImageView.tag = incomeData

            dateSalaryItemTextView.text = incomeData.incomeDate
            salaryItemTitleTextView.text = incomeData.incomeCategory
            sumSalaryItemTextView.text = incomeData.incomeSum.toString()
        }
    }


    override fun onClick(view: View?) {
        val incomeData = view?.tag as IncomeModel
        additionalListener.removeAdditional(incomeData.id)
    }


    class AdditionalHolder(val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}