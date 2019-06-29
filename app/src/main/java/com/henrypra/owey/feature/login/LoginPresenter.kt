package com.henrypra.owey.feature.login

import com.henrypra.owey.BaseActivity

class LoginPresenter(val activity: BaseActivity,
                     val actionListener: LoginActionListener,
                     val view: LoginContract.View) : LoginContract.Presenter {
    override fun onLoginClicked() {
        actionListener.onLoginClicked()
    }

}