package com.henrypra.owey.architecture

interface BasePresenter {

    fun onFragmentStart() {}

    fun onFragmentResume() {}

    fun onFragmentPause() {}

    fun onFragmentStop() {}

}