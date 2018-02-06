package com.gmb.madridshops.router

import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import com.gmb.madridshops.activity.MainActivity
import com.gmb.madridshops.activity.PicassoActivity

class Router {

    // hacer singletoooon
    fun navigateFromMainActivityToPicassoActivity(mainActivity: MainActivity) {
        mainActivity.startActivity(Intent(mainActivity, PicassoActivity::class.java))
    }
}
