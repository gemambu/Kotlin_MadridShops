package com.gmb.madridshops.activity

import android.content.Context
import android.os.Bundle
import android.support.v4.view.GestureDetectorCompat
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import com.gmb.madridshops.R
import com.gmb.madridshops.domain.interactor.ErrorCompletion
import com.gmb.madridshops.domain.interactor.SuccessCompletion
import com.gmb.madridshops.domain.interactor.checkentities.CheckEntitiesImpl
import com.gmb.madridshops.domain.interactor.getallentities.GetAllEntitiesInteractorImpl
import com.gmb.madridshops.domain.model.Entities
import com.gmb.madridshops.domain.util.EntityType
import com.gmb.madridshops.router.Router
import com.gmb.madridshops.util.*
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private var context: Context = this
    private var mDetector: GestureDetectorCompat? = null
    private var firstLoadCompleted = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mDetector = GestureDetectorCompat(this, MyGestureListener(this))

        manageActivityComponents(false)

        checkFirstLoad()

        main_activity_shops_button.setOnClickListener {
            Log.d(MainActivity::class.java.canonicalName, "Clicked on Shops")
            Router().navigateFromMainActivityToShopsActivity(this)
        }

        main_activity_activities_button.setOnClickListener {
            Log.d(MainActivity::class.java.canonicalName, "Clicked on Activities")
            Router().navigateFromMainActivityToActivitiesActivity(this)
        }

    }

    override fun onResume() {
        super.onResume()

        if(!firstLoadCompleted && !checkPreferences(this)) {
            checkFirstLoad()
        }

    }

    private fun checkFirstLoad() {
        if (!checkConnection(this) && !checkPreferences(this)) {

            // show no connection message
            val alertDialog = AlertDialog.Builder(this@MainActivity).create()

            alertDialog.setTitle("ERROR!")
            alertDialog.setMessage("There is no connection and no data!")
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Try again", { dialogInterface, i ->
                dialogInterface.cancel()
                checkFirstLoad()
            })
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Fun Time!", { dialogInterface, i ->
                dialogInterface.cancel()
                Router().navigateFromMainActivityToFunActivity(this)
            })
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Close App", { dialogInterface, i ->
                finish()
                System.exit(0)
            })

            alertDialog.show()

        } else {
            initializeData()
        }
    }

    private fun initializeData(){

        val checkEntities = CheckEntitiesImpl(this)
        var total = checkEntities.execute()

        if (total < 1){
            firstLoadCompleted = true
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
        } else {
            firstLoadCompleted = true
            updatePreferences(context)
            manageActivityComponents(true)
        }

    }

    private fun activitiesInteractor(context: Context) {
        val allActivitiesInteractor = GetAllEntitiesInteractorImpl(this)
        allActivitiesInteractor.execute(EntityType.ACTIVITY,
                success = object : SuccessCompletion<Entities> {
                    override fun successCompletion(e: Entities) {
                        Log.d(SUCCESS, "Activities count: " + e.count())

                        updatePreferences(context)
                        manageActivityComponents(true)

                    }

                }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Log.d(ERROR, errorMessage)
            }
        })
    }


    /******* Auxiliar methods to manage the view: progress bar, buttons... *******/

    private fun manageActivityComponents(status: Boolean) {
        when (status) {
            true -> {
                main_activity_progress_bar.visibility = View.GONE
                main_activity_activities_button.isEnabled = true
                main_activity_shops_button.isEnabled = true
            }
            false -> {
                main_activity_progress_bar.visibility = View.VISIBLE
                main_activity_progress_bar.animate()
                main_activity_activities_button.isEnabled = false
                main_activity_shops_button.isEnabled = false
            }
        }
    }

    /******* Gesture recognizer *******/

    override fun onTouchEvent(event: MotionEvent): Boolean {
        this.mDetector!!.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    internal inner class MyGestureListener(mainActivity: MainActivity) : GestureDetector.SimpleOnGestureListener() {

        private val mainAct = mainActivity

        override fun onFling(event1: MotionEvent, event2: MotionEvent,
                             velocityX: Float, velocityY: Float): Boolean {
            Log.d(DEBUG_TAG, "onFling: opening fun activity with secret gesture!")
            Router().navigateFromMainActivityToFunActivity(mainAct)
            return true
        }

        override fun onLongPress(e: MotionEvent?) {
            Log.d(DEBUG_TAG, "onLongPress: opening fun activity with secret gesture!")
            Router().navigateFromMainActivityToFunActivity(mainAct)
        }

    }


}