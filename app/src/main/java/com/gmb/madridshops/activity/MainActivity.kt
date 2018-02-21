package com.gmb.madridshops.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.gmb.madridshops.R
import com.gmb.madridshops.router.Router
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        disableButtons()
        displaySpinner()

        main_activity_shops_button.setOnClickListener {
            Log.d(MainActivity::class.java.canonicalName, "Clicked on Shops")
            Router().navigateFromMainActivityToShopsActivity(this)
        }

        main_activity_activities_button.setOnClickListener {
            Log.d(MainActivity::class.java.canonicalName, "Clicked on Activities")
            Router().navigateFromMainActivityToActivitiesActivity(this)
        }

    }

    fun displaySpinner(){

        main_activity_progress_bar.visibility = View.VISIBLE
        main_activity_progress_bar.animate()

    }

    fun hideSpinner(){

        main_activity_progress_bar.visibility = View.GONE

    }

    fun disableButtons(){
        main_activity_activities_button.isEnabled = false
        main_activity_shops_button.isEnabled = false
    }

    fun enableButtons(){
        main_activity_activities_button.isEnabled = true
        main_activity_shops_button.isEnabled = true
    }

}