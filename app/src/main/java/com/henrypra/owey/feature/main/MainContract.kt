package com.henrypra.owey.feature.main

import com.henrypra.owey.architecture.BasePresenter
import com.henrypra.owey.architecture.BaseView
import com.henrypra.owey.model.Debt

interface MainContract {

    interface Presenter : BasePresenter {

        fun retrieveDebtFromDatabase()

        fun createDebt()

    }

    interface View : BaseView<Presenter> {

        fun displayDebtList(debtList: List<Debt>)
    }
}