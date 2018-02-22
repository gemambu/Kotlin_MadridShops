package com.gmb.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.gmb.madridshops.util.APP

class MadridShopsApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d(APP, "onCreate")
/*
        val allShopsInteractor = GetAllEntitiesInteractorImpl(this)

        allShopsInteractor.execute(EntityType.SHOP,
                success = object : SuccessCompletion<Entities> {
                    override fun successCompletion(e: Entities) {
                        Log.d(SUCCESS, "Shops count: " + e.count())
                        activitiesInteractor()
                    }

                }, error = object : ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d(ERROR, errorMessage)
                    }
        })



    }

    private fun activitiesInteractor() {
        val allActivitiesInteractor = GetAllEntitiesInteractorImpl(this)
        allActivitiesInteractor.execute(EntityType.ACTIVITY,
                success = object : SuccessCompletion<Entities> {
                    override fun successCompletion(e: Entities) {
                        Log.d(SUCCESS, "Activities count: " + e.count())
                    }

                }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Log.d(ERROR, errorMessage)
            }
        })*/
    }

}

