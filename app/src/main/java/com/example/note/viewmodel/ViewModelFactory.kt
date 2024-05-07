package com.example.note.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.example.note.db.dao.NoteDao
import com.example.note.repository.NoteRepository
import com.example.note.repository.UserRepository
import com.example.note.utils.PreferenceManager

class ViewModelFactory(
    private val noteDao: NoteDao?,
    private val preferenceManager: PreferenceManager,
    private val navController: NavController
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(NoteRepository(noteDao!!), UserRepository(preferenceManager), navController) as T
        }else if(modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(UserRepository(preferenceManager), navController) as T
        }else if(modelClass.isAssignableFrom(RegisterViewModel::class.java)) {
            return RegisterViewModel(UserRepository(preferenceManager), navController) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}