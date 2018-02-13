package com.gmb.madridshops.router

import android.content.Intent
import com.gmb.madridshops.activity.EntityDetailActivity
import com.gmb.madridshops.activity.MainActivity
import com.gmb.madridshops.activity.ShopsActivity
import com.gmb.madridshops.domain.model.Shop

class Router {

    fun navigateFromMainActivityToShopsActivity(mainActivity: MainActivity) {
        mainActivity.startActivity(Intent(mainActivity, ShopsActivity::class.java))
    }

    fun navigateFromMainActivityToActivitiesActivity(mainActivity: MainActivity) {
        //mainActivity.startActivity((Intent(mainActivity, TestActivity::class.java)))
    }

    fun navigateFromListActivityToDetailActivity(listActivity: ShopsActivity, selectedItem: Shop) {
        val intent = Intent(listActivity, EntityDetailActivity::class.java)
        intent.putExtra("EXTRA_SELECTED_ENTITY", selectedItem)
        listActivity.startActivity(intent)
    }
}
