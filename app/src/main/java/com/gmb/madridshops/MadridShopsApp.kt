package com.gmb.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.gmb.madridshops.domain.interactor.ErrorCompletion
import com.gmb.madridshops.domain.interactor.SuccessCompletion
import com.gmb.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImpl
import com.gmb.madridshops.domain.model.Entities

class MadridShopsApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App", "onCreate")

        val allShopsInteractor = GetAllShopsInteractorImpl(this)

        allShopsInteractor.execute(
                success = object : SuccessCompletion<Entities> {
                    override fun successCompletion(e: Entities) {
                        Log.d("Shops", "count: " + e.count())
                    }

                }, error = object : ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d("ERROR", errorMessage)
                    }
        })

    /*    allShopsInteractor.execute(
                success = { entities: Shops ->

                }, error = { msg: String ->

                })*/

        /*
        DeleteAllShopsImpl(this).execute(error = {

        }, success = {
            Log.d("Success", "success")
        })*/

    }

}