package com.gmb.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.gmb.madridshops.domain.interactor.ErrorCompletion
import com.gmb.madridshops.domain.interactor.SuccessCompletion
import com.gmb.madridshops.domain.interactor.getallshops.GetAllEntitiesInteractorImpl
import com.gmb.madridshops.domain.model.Entities
import com.gmb.madridshops.domain.util.EntityType
import com.gmb.madridshops.util.ERROR
import com.gmb.madridshops.util.SUCCESS

class MadridShopsApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App", "onCreate")

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
        })
    }

}

