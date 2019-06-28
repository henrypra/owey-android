package com.henrypra.owey.feature.login

import com.henrypra.owey.architecture.BasePresenter
import com.henrypra.owey.architecture.BaseView

interface LoginContract {

    interface Presenter : BasePresenter

    interface View : BaseView<Presenter>

}