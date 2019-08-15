package com.henrypra.owey.feature.detail

import com.henrypra.owey.architecture.BasePresenter
import com.henrypra.owey.architecture.BaseView
import com.henrypra.owey.model.Debt

interface DetailContract {
    interface Presenter : BasePresenter {
        fun retrieveDebtForId()
    }

    interface View : BaseView<Presenter> {

        fun displayDebt(debt: Debt)
    }
}