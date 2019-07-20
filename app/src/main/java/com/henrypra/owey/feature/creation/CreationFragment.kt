package com.henrypra.owey.feature.creation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.henrypra.owey.R
import com.henrypra.owey.architecture.BaseContractFragment
import kotlinx.android.synthetic.main.fragment_creation.*

class CreationFragment : BaseContractFragment<CreationContract.Presenter>(), CreationContract.View, View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_creation, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnCLickListeners()
    }

    private fun initOnCLickListeners() {
        btn_create_debt?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_create_debt -> {
                if (!edt_amount_currency?.text.isNullOrEmpty()) {
                    val amount: Double = edt_amount_currency?.text.toString().toDouble()
                    presenter?.createDebt(amount)
                }
            }
        }
    }

}