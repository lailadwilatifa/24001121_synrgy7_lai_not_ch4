package com.example.note.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.note.R
import com.example.note.repository.UserRepository
import com.example.note.utils.PreferenceManager

class LoginViewModel(val userRepository: UserRepository, val navController: NavController) :
    ViewModel() {
    var _inputEmail = MutableLiveData<String>()
    var _inputPassword = MutableLiveData<String>()

    private val inputEmail: LiveData<String> = _inputEmail
    private val inputPassword: LiveData<String> = _inputPassword

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    init {
        checkLogin()
    }

    private fun checkLogin() {
        if (getBoolean(PreferenceManager.KEY_IS_LOGGED_IN)) {
            navController.navigate(
                R.id.action_loginFragment_to_homeFragment,
                null,
                NavOptions.Builder().setPopUpTo(R.id.loginFragment, true).build()
            )
        }
    }

    fun onLogin() {
        val email = inputEmail.value
        val password = inputPassword.value
        val savedEmail = getString(PreferenceManager.KEY_EMAIL)
        val savedPassword = getString(PreferenceManager.KEY_PASSWORD)

        if (!email.isNullOrEmpty() && !password.isNullOrEmpty()) {
            if (email == savedEmail && password == savedPassword) {
                val keyValue = mapOf(
                    PreferenceManager.KEY_IS_LOGGED_IN to true
                )
                setValue(keyValue)
                navController.navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                _toastMessage.value = "Email atau Password tidak sesuai"
            }
        } else {
            _toastMessage.value = "Email dan Password tidak boleh kosong"
        }

    }

    fun onToRegister() {
        navController.navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun getString(key: String): String? {
        return userRepository.getString(key)
    }

    private fun getBoolean(key: String): Boolean {
        return userRepository.getBoolean(key)
    }

    fun setValue(keyValueMap: Map<String, Any>) {
        userRepository.setValues(keyValueMap)
    }
}