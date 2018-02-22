package com.gmb.madridshops.activity

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import com.gmb.madridshops.R
import com.gmb.madridshops.domain.interactor.ErrorCompletion
import com.gmb.madridshops.domain.interactor.SuccessCompletion
import com.gmb.madridshops.domain.interactor.getallshops.GetAllEntitiesInteractorImpl
import com.gmb.madridshops.domain.model.Entities
import com.gmb.madridshops.domain.util.EntityType
import com.gmb.madridshops.router.Router
import com.gmb.madridshops.util.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private var viewHasBeenSet = false
    private var context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!viewHasBeenSet){

            if ((!checkPreferences(this)) && (!checkConnection(this))) {

                // show no connection message
                val alertDialog = AlertDialog.Builder(this@MainActivity).create()

                alertDialog.setTitle("ERROR!")
                alertDialog.setMessage("There is no connection and no data!")
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK", {
                    dialogInterface, i ->
                    Snackbar.make(findViewById(android.R.id.content),
                            "Please, enable the connection or find a WIFI :)", Snackbar.LENGTH_LONG)
                            .show()

                    // TODO complete or retry again ???
                })
                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Close App", {
                    dialogInterface, i -> finish()
                    System.exit(0)
                })

                alertDialog.show()

            } else {
                initializeData()
                viewHasBeenSet = true

            }
        }

        main_activity_shops_button.setOnClickListener {
            Log.d(MainActivity::class.java.canonicalName, "Clicked on Shops")
            Router().navigateFromMainActivityToShopsActivity(this)
        }

        main_activity_activities_button.setOnClickListener {
            Log.d(MainActivity::class.java.canonicalName, "Clicked on Activities")
            Router().navigateFromMainActivityToActivitiesActivity(this)
        }

    }

    private fun initializeData(){

        disableButtons()
        displaySpinner()

        val allShopsInteractor = GetAllEntitiesInteractorImpl(this)

        allShopsInteractor.execute(EntityType.SHOP,
                success = object : SuccessCompletion<Entities> {
                    override fun successCompletion(e: Entities) {
                        Log.d(SUCCESS, "Shops count: " + e.count())
                        activitiesInteractor(context)
                    }

                }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Log.d(ERROR, errorMessage)
            }
        })
    }

    private fun activitiesInteractor(context: Context) {
        val allActivitiesInteractor = GetAllEntitiesInteractorImpl(this)
        allActivitiesInteractor.execute(EntityType.ACTIVITY,
                success = object : SuccessCompletion<Entities> {
                    override fun successCompletion(e: Entities) {
                        Log.d(SUCCESS, "Activities count: " + e.count())

                        hideSpinner()
                        enableButtons()
                        updatePreferences(context)
                    }

                }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Log.d(ERROR, errorMessage)
            }
        })
    }

    /******* Auxiliar methods to manage the view: progress bar, buttons... *******/

    private fun displaySpinner(){

        main_activity_progress_bar.visibility = View.VISIBLE
        main_activity_progress_bar.animate()
    }

    private fun hideSpinner(){

        main_activity_progress_bar.visibility = View.GONE
    }

    private fun disableButtons(){
        main_activity_activities_button.isEnabled = false
        main_activity_shops_button.isEnabled = false
    }

    private fun enableButtons(){
        main_activity_activities_button.isEnabled = true
        main_activity_shops_button.isEnabled = true
    }

}
