package com.gmb.madridshops.router

import android.content.Intent
import com.gmb.madridshops.activity.MainActivity
import com.gmb.madridshops.activity.ShopsActivity

class Router {

    fun navigateFromMainActivityToShopsActivity(mainActivity: MainActivity) {
        mainActivity.startActivity(Intent(mainActivity, ShopsActivity::class.java))
    }

    fun navigateFromMainActivityToActivitiesActivity(mainActivity: MainActivity) {
        //mainActivity.startActivity((Intent(mainActivity, TestActivity::class.java)))
    }
}
