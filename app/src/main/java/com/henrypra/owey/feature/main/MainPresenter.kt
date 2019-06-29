package com.henrypra.owey.feature.main

import com.henrypra.owey.BaseActivity

class MainPresenter(val activity: BaseActivity,
                    val actionListener: MainActionListener,
                    val view: MainContract.View) : MainContract.Presenter {


}