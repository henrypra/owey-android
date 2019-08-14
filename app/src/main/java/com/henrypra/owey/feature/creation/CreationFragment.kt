package com.henrypra.owey.feature.creation

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.henrypra.owey.R
import com.henrypra.owey.architecture.BaseContractFragment
import com.henrypra.owey.utility.DialogUtil
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

    private fun isValidInput(input: EditText?): Boolean {
        return if (TextUtils.isEmpty(input?.text.toString())) {
            input?.error = activity?.getString(R.string.error_code_empty_field)
            false
        } else {
            true
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_send -> {
                if (isValidInput(edt_title) && isValidInput(edt_amount) && isValidInput(edt_friend)) {
                    try {
                        val amount: Double? = edt_amount?.text.toString().toDouble()
                        val title: String = edt_title?.text.toString()
                        val friend: String = edt_friend?.text.toString()
                        val note: String = edt_note?.text.toString()
                        val isDebt: Boolean? = if (switch_debt != null) switch_debt?.isActivated else false

                        presenter?.createDebt(amount, title, friend, note, isDebt)
                    } catch (e: Exception) {
                        DialogUtil.buildCreationErrorDialog(context)
                        e.stackTrace
                    }
                }

            }
            R.id.btn_cancel -> activity?.finish()
        }
    }
}