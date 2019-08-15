package com.henrypra.owey.feature.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.henrypra.owey.R
import com.henrypra.owey.architecture.BaseContractFragment
import com.henrypra.owey.model.Debt
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : BaseContractFragment<DetailContract.Presenter>(), DetailContract.View, View.OnClickListener {

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
    }

    override fun displayDebt(debt: Debt) {
        edt_amount?.setText(debt.amount.toString() + debt.currency)
        tv_title?.text = debt.title
        edt_friend?.setText(debt.friend)
        edt_detail_note?.setText(debt.note)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_back -> activity?.finish()
        }
    }
}
