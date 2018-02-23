package com.gmb.madridshops.activity

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
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


class MainActivity : AppCompatActivity(){

    private var context: Context = this
    private var mDetector: GestureDetector? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        manageActivityComponents(false)
        mDetector = GestureDetector(this, MyGestureListener(this))

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
        checkFirstLoad()
    }

    private fun checkFirstLoad() {
        if (!checkConnection(this) && !checkPreferences(this)) {

            // show no connection message
            val alertDialog = AlertDialog.Builder(this@MainActivity).create()

            alertDialog.setTitle("ERROR!")
            alertDialog.setMessage("There is no connection and no data!")
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Try again", { dialogInterface, i ->
                checkFirstLoad()
            })
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Fun Time!", { dialogInterface, i ->
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

    private fun initializeData() {

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


}


class MyGestureListener(mainActivity: MainActivity) : GestureDetector.OnGestureListener {
    private val main: MainActivity = mainActivity
    override fun onShowPress(e: MotionEvent?) {
        Router().navigateFromMainActivityToFunActivity(main)
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        Router().navigateFromMainActivityToFunActivity(main)
        return true
    }

    override fun onDown(e: MotionEvent?): Boolean {
        Router().navigateFromMainActivityToFunActivity(main)
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        Router().navigateFromMainActivityToFunActivity(main)
        return true
    }

    override fun onLongPress(e: MotionEvent?) {
        Router().navigateFromMainActivityToFunActivity(main)
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        Router().navigateFromMainActivityToFunActivity(main)
        return true
    }
}
