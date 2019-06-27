package com.henrypra.owey.utility

import com.henrypra.owey.BaseActivity
import com.henrypra.owey.architecture.ToolbarParams

object ToolbarUtil {

    /**
     * All methods only apply those params that are given, all others are default attributes defined in the toolbar params class.
     */

    fun hideToolbar(activity: BaseActivity): ToolbarParams {
        return ToolbarParams(activity, hideToolbar = true)
    }

    fun createTitleOnlyParams(activity: BaseActivity, title: String?): ToolbarParams {
        return ToolbarParams(activity, title = title)
    }

    fun createTitleAndBackParams(activity: BaseActivity, title: String?, showBack: Boolean): ToolbarParams {
        return ToolbarParams(activity, title = title, showBackNavigation = showBack)
    }

    fun createTitleAndIconParams(activity: BaseActivity, title: String?, iconId: Int): ToolbarParams {
        return ToolbarParams(activity, title = title, navIconId = iconId)
    }

    fun createTitleIconAndBackParams(activity: BaseActivity, title: String?, showBack: Boolean, iconId: Int): ToolbarParams {
        return ToolbarParams(activity, title = title, showBackNavigation = showBack, navIconId = iconId)
    }

}