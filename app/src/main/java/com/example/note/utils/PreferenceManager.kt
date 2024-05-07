package com.example.note.utils

import android.content.Context
import android.content.SharedPreferences

class PreferenceManager (context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "UserPreference",
        Context.MODE_PRIVATE
    )

    companion object {
        const val KEY_USERNAME = "username"
        const val KEY_EMAIL = "email"
        const val KEY_PASSWORD = "password"
        const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    fun setValues(keyValueMap: Map<String, Any>){
        val editor = sharedPreferences.edit()
        keyValueMap.forEach { (key, value) ->
            when(value) {
                is String -> editor.putString(key, value)
                is Int -> editor.putInt(key, value)
                is Boolean -> editor.putBoolean(key, value)
                else -> throw IllegalArgumentException("Unsupported value type")
            }
        }
        editor.apply()
    }

    fun getString(key: String) : String? {
        return sharedPreferences.getString(key, "")
    }

    fun getBoolean(key: String) : Boolean {
        return sharedPreferences.getBoolean(key, false)
    }
}