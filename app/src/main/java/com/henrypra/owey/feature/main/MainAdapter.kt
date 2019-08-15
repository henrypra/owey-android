package com.henrypra.owey.feature.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.henrypra.owey.R
import com.henrypra.owey.model.Debt
import kotlinx.android.synthetic.main.item_debt.view.*

class MainAdapter(val context: Context?, var listener: DebtClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

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
        //TODO
        val currentDebt = debtList[position]
        holder.title.text = currentDebt.title
        holder.date.text = currentDebt.date
        holder.desc.text = ""
        holder.amount.text = currentDebt.amount.toString()
//        Picasso.get().load(currentDebt.owner?.avatarUrl).into(holder.avatar)

        holder.itemView.setOnClickListener {
            listener.onDebtClicked(currentDebt.id)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (debtList.isNotEmpty()) viewTypeList else viewTypeEmpty
    }

    class GistHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.tv_title
        val date = view.tv_date
        val desc = view.tv_desc
        val avatar = view.img_avatar
        val amount = view.tv_amount
    }

    class EmptyHolder(view: View) : RecyclerView.ViewHolder(view)

    interface DebtClickListener {
        fun onDebtClicked(id: Int)
    }
}