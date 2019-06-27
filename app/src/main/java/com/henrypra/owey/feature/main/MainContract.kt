package com.henrypra.owey.feature.main

import com.henrypra.owey.architecture.BasePresenter
import com.henrypra.owey.architecture.BaseView

interface MainContract {

    interface Presenter : BasePresenter

    interface View : BaseView<Presenter>
}