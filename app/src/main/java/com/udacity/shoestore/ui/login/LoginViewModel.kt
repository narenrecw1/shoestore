package com.udacity.shoestore.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

private val EMAIL_REGEX = "^[A-Za-z](.*)([@])(.+)(\\.)(.+)".toRegex()

class LoginViewModel : ViewModel() {
    val email = MutableLiveData("")
    val password = MutableLiveData("")

    private val _isLoginEnabled = MutableLiveData(false)

    val isLoginEnabled: LiveData<Boolean>
        get() = _isLoginEnabled

    fun fieldsUpdated() {
        _isLoginEnabled.value = !email.value.isNullOrBlank()
                && !password.value.isNullOrBlank()
                && EMAIL_REGEX.matches(email.value!!)
    }
}