package com.app.dubkiapp.preferences

import android.content.Context
import javax.inject.Inject

class AppPreferenceImpl @Inject constructor(context: Context): AppPreference {

    companion object {
        const val REVISION = "revision"
        const val THEME = "theme"
    }

    private val preference = context.getSharedPreferences("dagger-pref", Context.MODE_PRIVATE)
    private val editor = preference.edit()


    private fun saveString(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    private fun getString(key: String, defaultValue: String = ""): String {
        return preference.getString(key, defaultValue) ?: defaultValue
    }

    private fun saveInt(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    private fun getInt(key: String, defaultValue: Int = 0): Int {
        return preference.getInt(key, defaultValue)
    }


    override fun getRevision(): Int {
        return getInt(REVISION)
    }

    override fun setRevision(revision: Int) {
        saveInt(REVISION, revision)
    }

    override fun getTheme(): String {
        return getString(THEME)
    }

    override fun setTheme(theme: String) {
        saveString(THEME, theme)
    }

}