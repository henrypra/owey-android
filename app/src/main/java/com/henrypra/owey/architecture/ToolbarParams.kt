package com.henrypra.owey.architecture

import com.henrypra.owey.BaseActivity
import com.henrypra.owey.R

class ToolbarParams(val activity: BaseActivity?,
                    val title: String? = "",
                    val subTitle: String? = null,
                    val showBackNavigation: Boolean = false,
                    val navIconId: Int = R.drawable.ic_arrow_back,
                    val hideToolbar: Boolean = false) {

    init {
        activity?.setupToolbar(this)
    }

}