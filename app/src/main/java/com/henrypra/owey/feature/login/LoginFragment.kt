package com.henrypra.owey.feature.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.henrypra.owey.R
import com.henrypra.owey.architecture.BaseContractFragment
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : BaseContractFragment<LoginContract.Presenter>(), LoginContract.View, View.OnClickListener {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initOnClickListeners()
    }

    private fun initOnClickListeners() {
        btn_login_save?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_login_save -> {
                presenter?.onLoginClicked()
            }
        }
    }
}
