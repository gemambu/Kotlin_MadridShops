package com.gmb.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.gmb.madridshops.R
import com.gmb.madridshops.router.Router
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_activity_shops_button.setOnClickListener {
            Log.d(MainActivity::class.java.canonicalName, "Clicked on Shops")
            Router().navigateFromMainActivityToShopsActivity(this)
        }

        main_activity_activities_button.setOnClickListener {
            Log.d(MainActivity::class.java.canonicalName, "Clicked on Activities")
            Router().navigateFromMainActivityToActivitiesActivity(this)
        }

    }

}