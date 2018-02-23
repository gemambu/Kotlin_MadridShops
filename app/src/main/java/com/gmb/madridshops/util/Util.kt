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

    val preferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
    val userPreference = preferences.getBoolean(USER_PREFERENCES, false)

    return userPreference
}

fun updatePreferences(context: Context) {
//    val settings = PreferenceManager.getDefaultSharedPreferences(context)
//    val editor: SharedPreferences.Editor = settings.edit()


    val preferences = context.getSharedPreferences(USER_PREFERENCES, Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = preferences.edit()
    editor.putBoolean(USER_PREFERENCES, true)
    editor.apply()
}

fun checkConnection(context: Context): Boolean {

    val conMgr: ConnectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val netInfo: NetworkInfo? = conMgr.activeNetworkInfo

    return netInfo != null
}

fun getGoogleMapUrl(latitude: Float, longitude: Float) = "https://maps.googleapis.com/maps/api/staticmap?center=${latitude},${longitude}&maptype=roadmap&format=png&zoom=17&size=320x220&scale=2&markers=%7Ccolor:0x9C7B14%7C${latitude},${longitude}"

