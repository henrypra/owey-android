package com.henrypra.owey.feature.category

import com.henrypra.owey.architecture.BasePresenter
import com.henrypra.owey.architecture.BaseView
import com.henrypra.owey.model.Debt

interface CategoryContract {

    interface Presenter : BasePresenter {

        fun retrieveDebtFromDatabase()

        fun onDebtClicked(id: Int)

    }

    interface View : BaseView<Presenter> {

        fun displayDebtList(debtList: List<Debt>)
    }
}