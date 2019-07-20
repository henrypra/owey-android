package com.henrypra.owey.feature.creation

import com.henrypra.owey.architecture.BasePresenter
import com.henrypra.owey.architecture.BaseView

interface CreationContract {

    interface Presenter : BasePresenter {
        fun createDebt(amount: Double)
    }

    interface View : BaseView<Presenter>

}