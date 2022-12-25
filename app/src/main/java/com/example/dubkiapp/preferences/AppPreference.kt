package com.example.dubkiapp.preferences

interface AppPreference {

    fun getRevision(): Int
    fun setRevision(revision: Int)

}