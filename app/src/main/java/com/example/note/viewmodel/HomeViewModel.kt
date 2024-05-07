package com.example.note.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import com.example.note.R
import com.example.note.db.entities.Note
import com.example.note.repository.NoteRepository
import com.example.note.repository.UserRepository
import com.example.note.utils.PreferenceManager
import kotlinx.coroutines.launch

class HomeViewModel(
    val noteRepository: NoteRepository,
    val userRepository: UserRepository,
    val navController: NavController
) : ViewModel() {
    val getAllNotes = noteRepository.getAllNotes()

    fun getUsername(): String {
        return userRepository.getString(PreferenceManager.KEY_USERNAME) ?: ""
    }

    fun onLogout() {
        val keyValue = mapOf(
            PreferenceManager.KEY_IS_LOGGED_IN to false
        )
        userRepository.setValues(keyValue)
        navController.navigate(
            R.id.action_homeFragment_to_loginFragment,
            null,
            NavOptions.Builder().setPopUpTo(R.id.homeFragment, true).build()
        )
    }

    fun insert(note: Note) {
        viewModelScope.launch {
            noteRepository.insert(note)
        }
    }

    fun deleteNote(key: Int) {
        viewModelScope.launch {
            noteRepository.delete(key)
        }
    }

    fun update(note: Note) {
        viewModelScope.launch {
            noteRepository.update(note)
        }
    }
}