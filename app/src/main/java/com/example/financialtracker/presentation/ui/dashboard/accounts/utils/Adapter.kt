package com.example.financialtracker.presentation.ui.dashboard.accounts.utils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.financialtracker.databinding.RecyclerviewItemBinding
import com.example.financialtracker.domain.model.AccountsModel


class NoteDiffUtil (
    private val oldList: List<AccountsModel>,
    private val newList: List<AccountsModel>
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

interface Listener {
    fun remove(id : Int)
}
class Adapter (
    private val listener: Listener,
) :
    RecyclerView.Adapter<Adapter.Holder>(), View.OnClickListener{
    var accountsData : List<AccountsModel> = emptyList()
        set(newValue) {
            val diffUtil = NoteDiffUtil(field, newValue)
            val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
            field = newValue
            diffUtilResult.dispatchUpdatesTo(this@Adapter)
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = RecyclerviewItemBinding.inflate(inflater, parent, false)
        binding.deleteIconItemImageView.setOnClickListener(this)
        Log.d("AccountsDataAdapter", "I was created")
        return Holder(binding)
    }


    override fun getItemCount(): Int {
        return accountsData.size}

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val accountsData = accountsData[position]
        with(holder.binding) {
            Log.d("AccountsDataAdapter", "I was binded")
            deleteIconItemImageView.tag = accountsData

            dateSalaryItemTextView.text = accountsData.accountsDate
            Log.d("AccountsDataAdapter", accountsData.accountsDate)
            salaryItemTitleTextView.text = accountsData.accountsCategory
            sumSalaryItemTextView.text = accountsData.accountsSum.toString()
        }
    }


    override fun onClick(view: View?) {
        val accountsData = view?.tag as AccountsModel
        listener.remove(accountsData.id)
    }


    class Holder(val binding: RecyclerviewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
    }
}