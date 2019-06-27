package com.henrypra.owey.architecture

class PresenterNullException internal constructor(className: String) :
    RuntimeException("Presenter for $className is null")