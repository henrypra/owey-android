package com.henrypra.owey.architecture

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment

abstract class BaseContractFragment<T : BasePresenter> : Fragment(), BaseView<T> {

    override var presenter: T? = null

    override fun getCurrentContext(): Context? {
        return context
    }

    override val fragment: Fragment
        get() = this

    @Nullable
    @Override
    @CallSuper
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        if (presenter == null) {
            throw PresenterNullException(this.javaClass.simpleName)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    protected fun validateNonBlank(input: String?, message: Int): Boolean {
        val blank = input != null && input.trim { it <= ' ' }.isEmpty()
        if (blank) {
            error(message)
        }
        return !blank
    }

    override fun onStart() {
        super.onStart()
        presenter?.onFragmentStart()
    }

    override fun onResume() {
        super.onResume()
        presenter?.onFragmentResume()
    }

    override fun onPause() {
        super.onPause()
        presenter?.onFragmentPause()

    }

    @CallSuper
    override fun onStop() {
        super.onStop()
        presenter?.onFragmentStop()
    }
}