package com.henrypra.owey.feature.creation

import com.henrypra.owey.architecture.BasePresenter
import com.henrypra.owey.architecture.BaseView

interface CreationContract {

    interface Presenter : BasePresenter {
        fun createDebt(amount: Double?, currency: String?,  title: String?, friend: String, note: String?, isDebt: Boolean?)
    }

    interface View : BaseView<Presenter>

}