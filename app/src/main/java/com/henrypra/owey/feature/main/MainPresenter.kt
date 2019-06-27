package com.henrypra.owey.feature.main

import com.henrypra.owey.BaseActivity
import com.henrypra.owey.utility.ToolbarUtil

class MainPresenter(val activity: BaseActivity,
                    val actionListener: MainActionListener,
                    val view: MainContract.View) : MainContract.Presenter {
    init {
        ToolbarUtil.createTitleOnlyParams(activity, "Home")
    }

}