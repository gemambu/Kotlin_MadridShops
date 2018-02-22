package com.gmb.madridshops.util

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.preference.PreferenceManager

/**
 * Auxiliar methids to check the connection and preferences when the App is started
 */

fun checkPreferences(context: Context): Boolean {

    val settings = PreferenceManager.getDefaultSharedPreferences(context)
    val preference = settings.getBoolean(USER_PREFERENCES, false)

    return preference != null
}

fun updatePreferences(context: Context) {
    val settings = PreferenceManager.getDefaultSharedPreferences(context)
    val editor: SharedPreferences.Editor = settings.edit()

    editor.putBoolean(USER_PREFERENCES, true)
    editor.apply()
}

fun checkConnection(context: Context): Boolean {

    val conMgr: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo: NetworkInfo = conMgr.activeNetworkInfo

    return netInfo != null
}

