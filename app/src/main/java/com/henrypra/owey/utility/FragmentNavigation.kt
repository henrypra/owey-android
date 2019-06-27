package com.henrypra.owey.utility

import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.henrypra.owey.BaseActivity

/**
 * Null-safe navigation class for fragments
 */
class FragmentNavigation(private val id: Int,
                         private val activity: BaseActivity?) {

    /**
     * Replaces current fragment without adding it to the backstack
     *
     * @param fragment Fragment to display
     */
    fun replaceFragment(fragment: Fragment) {
        clearBackstack()
        //As seen safe calls '?' can be chained to not have to check each part
        activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(id, fragment)
                ?.commit()
    }

    /**
     * Replaces current fragment and adding it to the backstack
     *
     * @param fragment Fragment to display
     */
    fun replaceFragmentAndAddToBackStack(fragment: Fragment) {
        activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(id, fragment)
                ?.addToBackStack(null)
                ?.commit()
    }

    fun addChildFragment(fragment: Fragment, id: Int) {
        activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(id, fragment)
                ?.commit()
    }

    /**
     * Get current fragment that is displayed in the container
     *
     * @return current displayed fragment
     */
    fun getCurrentFragment(): Fragment? {
        // This function will return a Fragment or null. The function in Kotlin will explicitly mention this upon working with the result
        // This can be very useful if null is a proper state that you would want to process
        return activity?.supportFragmentManager?.findFragmentById(id)
    }

    /**
     * Shows a DialogFragment
     */
    fun showDialogFragment(dialogFragment: DialogFragment?) {
        dialogFragment?.show(activity?.supportFragmentManager, "")
    }

    /**
     * Checks if current backstack is empty
     *
     * @return boolean if the backstack is empty
     */
    fun isBackstackEmpty(): Boolean {
        return activity?.supportFragmentManager?.backStackEntryCount == 0
    }

    /**
     * clears the backstack
     */
    fun clearBackstack() {
        activity?.supportFragmentManager?.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

}