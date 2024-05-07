package com.example.note.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.note.R
import com.example.note.repository.UserRepository
import com.example.note.utils.PreferenceManager

class RegisterViewModel(val userRepository: UserRepository, val navController: NavController) :
    ViewModel() {
    var _inputUsername = MutableLiveData<String>()
    var _inputEmail = MutableLiveData<String>()
    var _inputPassword = MutableLiveData<String>()
    var _inputConfirmPassword = MutableLiveData<String>()

    private val inputUsername: LiveData<String> = _inputUsername
    private val inputEmail: LiveData<String> = _inputEmail
    private val inputPassword: LiveData<String> = _inputPassword
    private val inputConfirmPassword: LiveData<String> = _inputConfirmPassword

    private val _toastMessage = MutableLiveData<String>()
    val toastMessage: LiveData<String> get() = _toastMessage

    fun onDaftar() {
        val username = inputUsername.value
        val email = inputEmail.value
        val password = inputPassword.value
        val confirmPassword = inputConfirmPassword.value

        if (username.isNullOrBlank() && email.isNullOrBlank() && password.isNullOrBlank() && confirmPassword.isNullOrBlank()) {
            _toastMessage.value = "Semua harus diisi"
        } else {
            if (password == confirmPassword) {
                val keyValue = mapOf(
                    PreferenceManager.KEY_USERNAME to username.toString(),
                    PreferenceManager.KEY_EMAIL to email.toString(),
                    PreferenceManager.KEY_PASSWORD to password.toString(),
                    PreferenceManager.KEY_IS_LOGGED_IN to true
                )
                setValue(keyValue)
                navController.navigate(
                    R.id.action_registerFragment_to_homeFragment,
                    null,
                    NavOptions.Builder().setPopUpTo(R.id.registerFragment, true).build()
                )
            } else {
                _toastMessage.value = "Konfirmasi password berbeda"
            }
        }
    }

    fun setValue(keyValueMap: Map<String, Any>) {
        userRepository.setValues(keyValueMap)
    }
}