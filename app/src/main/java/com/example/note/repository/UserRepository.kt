package com.example.note.repository

import com.example.note.utils.PreferenceManager

class UserRepository (private val preferenceManager: PreferenceManager) {
    fun setValues(keyValueMap: Map<String, Any>){
        preferenceManager.setValues(keyValueMap)
    }

    fun getString(key: String): String? {
        return preferenceManager.getString(key)
    }

    fun getBoolean(key: String): Boolean {
        return preferenceManager.getBoolean(key)
    }
}
