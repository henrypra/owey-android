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
        btn_send?.setOnClickListener(this)
        btn_cancel?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_send -> {
                val amount: Double? = edt_amount?.text.toString().toDoubleOrNull()
                val title: String = edt_title?.text.toString()
                val friend: String = edt_friend?.text.toString()
                val note: String = edt_note?.text.toString()
                val isDebt: Boolean? = if (switch_debt != null) switch_debt?.isActivated else false

                if (validateNonBlank(amount.toString(), R.string.title)
                        && validateNonBlank(title, R.string.title)
                        && validateNonBlank(friend, R.string.title)
                        && validateNonBlank(note, R.string.title)) {

                    presenter?.createDebt(amount, title, friend, note, isDebt)
                }


            }
            R.id.btn_cancel -> activity?.finish()
        }
    }
}