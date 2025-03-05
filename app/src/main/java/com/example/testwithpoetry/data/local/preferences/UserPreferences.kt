package com.example.testwithpoetry.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import com.example.testwithpoetry.domain.models.User
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(@ApplicationContext context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        private const val KEY_USER = "user_data"
    }

    fun saveUser(user: User) {
        val json = gson.toJson(user)
        prefs.edit().putString(KEY_USER, json).apply()
    }

    fun getUser(): User? {
        val json = prefs.getString(KEY_USER, null) ?: return null
        return gson.fromJson(json, User::class.java)
    }

    fun getUserName(): String {
        val json = prefs.getString(KEY_USER, null) ?: return ""
        return gson.fromJson(json, User::class.java).name
    }

    fun clearUser() {
        prefs.edit().remove(KEY_USER).apply()
    }

    fun hasUser() = prefs.getString(KEY_USER, null) != null
}