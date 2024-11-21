package com.marvel.app.common.utils

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPrefs @Inject constructor(@ApplicationContext context: Context) {

    private lateinit var prefs: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor

    companion object {
        const val USER_PROFILE = "user_profile"
        const val USER_TOKEN = "user_token"
        const val ONBOARDING = "ONBOARDING"
        const val KEY_FAIR_ID = "KEY_FAIR_ID"
        const val SHARED_PREFS = "cairo_book_fair_prefs"
    }

    init {
        prefs = context.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE)
        editor = prefs.edit()
    }

    fun clear() {
        editor.clear()
        editor.commit()
    }

    fun saveString(key: String, value: String?) {
        editor.putString(key, value).commit()
    }

    fun getStringPref(key: String): String? {
        return prefs.getString(key, "")
    }

    fun saveInt(key: String, value: Int) {
        editor.putInt(key, value).commit()
    }

    fun getIntPref(key: String): Int {
        return prefs.getInt(key, -1)
    }

    fun getBoolean(key: String): Boolean {
        return prefs.getBoolean(key, false)
    }

    fun saveBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value).commit()
    }

}