package com.henrypra.owey.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import com.henrypra.owey.R
import com.henrypra.owey.architecture.BaseContractFragment
import com.henrypra.owey.model.Debt
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : BaseContractFragment<DetailContract.Presenter>(), DetailContract.View, View.OnClickListener, PopupMenu.OnMenuItemClickListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClickListeners()
        presenter?.retrieveDebtForId()
    }

    private fun initOnClickListeners() {
        btn_back?.setOnClickListener(this)
        btn_more?.setOnClickListener(this)
    }

    override fun displayDebt(debt: Debt) {
        switch_debt?.checkedTogglePosition = if (debt.isDebt == true) 1 else 0
        edt_amount?.setText(debt.amount.toString() + debt.currency)
        tv_title?.text = debt.title
        edt_friend?.setText(debt.friend)
        edt_detail_note?.setText(debt.note)
    }

    private fun openOptionsMenu(view: View) {
        val popup = context?.let { PopupMenu(it, view) }
        val inflater = popup?.menuInflater
        inflater?.inflate(R.menu.options_detail_menu, popup.menu)
        popup?.show()
        popup?.setOnMenuItemClickListener(this)
    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        return when {
            item?.itemId == R.id.action_edit -> {
                Toast.makeText(context, "On edit clicked", Toast.LENGTH_LONG).show()
                true
            }
            item?.itemId == R.id.action_delete -> {
                presenter?.deleteDebtForId()
                true
            }
            else -> false
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.btn_back -> activity?.finish()
            R.id.btn_more -> openOptionsMenu(view)
        }
    }
}
