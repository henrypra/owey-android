package com.henrypra.owey.architecture

import android.content.Context
import androidx.fragment.app.Fragment

interface BaseView<T : BasePresenter> {

    fun getCurrentContext(): Context?

    var presenter: T?

    val fragment: Fragment

}