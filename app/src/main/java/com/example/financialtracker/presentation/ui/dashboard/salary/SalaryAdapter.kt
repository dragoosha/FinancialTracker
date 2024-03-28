package com.example.financialtracker.presentation.ui.dashboard.salary

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.financialtracker.databinding.SalaryRecyclerviewItemBinding
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

interface SalaryListener {
    fun removeNote(id : Int)
}

class SalaryAdapter (private val salaryListener: SalaryListener) :
   RecyclerView.Adapter<SalaryAdapter.SalaryHolder>(), View.OnClickListener{

    var data : List<IncomeModel> = emptyList()
        set(newValue) {
            val diffUtil = NoteDiffUtil(field, newValue)
            val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
            field = newValue
            diffUtilResult.dispatchUpdatesTo(this@SalaryAdapter)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SalaryHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = SalaryRecyclerviewItemBinding.inflate(inflater, parent, false)
        binding.deleteIconItemImageView.setOnClickListener(this)

        return SalaryHolder(binding)
    }


    override fun getItemCount(): Int {
        return data.size}

    override fun onBindViewHolder(holder: SalaryHolder, position: Int) {
        val incomeData = data[position]
        with(holder.binding) {
            deleteIconItemImageView.tag = incomeData

            dateSalaryItemTextView.text = incomeData.incomeDate
            salaryItemTitleTextView.text = incomeData.incomeCategory
            sumSalaryItemTextView.text = incomeData.incomeSum.toString()
        }
    }


    override fun onClick(view: View?) {
        val incomeData = view?.tag as IncomeModel
        salaryListener.removeNote(incomeData.id)
    }


    class SalaryHolder(val binding: SalaryRecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}