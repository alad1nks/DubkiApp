package com.app.dubkiapp.preferences

interface AppPreference {

    fun getRevision(): Int
    fun setRevision(revision: Int)

    fun getTheme(): String
    fun setTheme(theme: String)

}