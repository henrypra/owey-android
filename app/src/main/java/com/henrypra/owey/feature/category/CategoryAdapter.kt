package com.henrypra.owey.feature.category

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.henrypra.owey.R
import com.henrypra.owey.model.Debt
import kotlinx.android.synthetic.main.item_debt.view.*

class CategoryAdapter(val context: Context?, var listener: DebtClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var debtList = mutableListOf<Debt>()

    private val viewTypeEmpty = 0
    private val viewTypeList = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            viewTypeList -> GistHolder(LayoutInflater.from(context).inflate(R.layout.item_debt, parent, false))
            else -> EmptyHolder(LinearLayout(context))
        }
    }

    override fun getItemCount(): Int {
        return debtList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GistHolder -> initGist(holder, position)
        }
    }

    private fun initGist(holder: GistHolder, position: Int) {
        val currentDebt = debtList[position]
        if (currentDebt.isDebt == true) {
            holder.amount.setTextColor(context?.let { ContextCompat.getColorStateList(it, R.color.orange) })
            holder.border.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.orange) }
        } else {
            holder.amount.setTextColor(context?.let { ContextCompat.getColorStateList(it, R.color.light_green) })
            holder.border.backgroundTintList = context?.let { ContextCompat.getColorStateList(it, R.color.light_green) }
        }
        holder.title.text = currentDebt.title
        holder.friend.text = currentDebt.friend
        holder.amount.text = currentDebt.amount.toString() + currentDebt.currency
        holder.avatar.text = currentDebt.friend.first().toString().toUpperCase()

        holder.itemView.setOnClickListener {
            listener.onDebtClicked(currentDebt.id)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (debtList.isNotEmpty()) viewTypeList else viewTypeEmpty
    }

    class GistHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title = view.tv_title
        var friend = view.tv_friend
        var avatar = view.tv_letter_friend
        var amount = view.tv_amount
        var border = view.border
    }

    class EmptyHolder(view: View) : RecyclerView.ViewHolder(view)

    interface DebtClickListener {
        fun onDebtClicked(id: Int)
    }
}