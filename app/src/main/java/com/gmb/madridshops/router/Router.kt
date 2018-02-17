package com.gmb.madridshops.router

import android.content.Intent
import com.gmb.madridshops.activity.EntityDetailActivity
import com.gmb.madridshops.activity.EntityListActivity
import com.gmb.madridshops.activity.MainActivity
import com.gmb.madridshops.domain.model.Entity
import com.gmb.madridshops.domain.util.EntityType
import com.gmb.madridshops.util.EXTRA_ENTITY_TYPE
import com.gmb.madridshops.util.EXTRA_SELECTED_ENTITY

class Router {

    fun navigateFromMainActivityToShopsActivity(mainActivity: MainActivity) {
        navigateToDetailActivity(mainActivity, EntityType.SHOP)
    }

    fun navigateFromMainActivityToActivitiesActivity(mainActivity: MainActivity) {
        navigateToDetailActivity(mainActivity, EntityType.ACTIVITY)
    }

    private fun navigateToDetailActivity(mainActivity: MainActivity, type: EntityType) {
        val intent = Intent(mainActivity, EntityListActivity::class.java)
        intent.putExtra(EXTRA_ENTITY_TYPE, type)
        mainActivity.startActivity(intent)
    }

    fun navigateFromListActivityToDetailActivity(listActivity: EntityListActivity, selectedItem: Entity) {
        val intent = Intent(listActivity, EntityDetailActivity::class.java)
        intent.putExtra(EXTRA_SELECTED_ENTITY, selectedItem)
        listActivity.startActivity(intent)
    }
}
