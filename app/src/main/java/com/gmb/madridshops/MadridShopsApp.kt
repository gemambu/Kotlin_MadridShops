package com.gmb.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.gmb.madridshops.domain.interactor.ErrorCompletion
import com.gmb.madridshops.domain.interactor.SuccessCompletion
import com.gmb.madridshops.domain.interactor.deleteallshops.DeleteAllShopsImpl
import com.gmb.madridshops.domain.interactor.getallshops.GetAllShopsInteractorFakeImpl
import com.gmb.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImpl
import com.gmb.madridshops.domain.model.Shops

class MadridShopsApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App", "onCreate")

        val allShopsInteractor = GetAllShopsInteractorImpl(this)

        allShopsInteractor.execute(
                success = object : SuccessCompletion<Shops> {
                    override fun successCompletion(e: Shops) {
                        Log.d("Shops", "count: " + e.count())

                       /* e.shops.forEach {
                            Log.d("Shop", it.name)
                        }*/
                    }

                }, error = object : ErrorCompletion {
                    override fun errorCompletion(errorMessage: String) {
                        Log.d("ERROR", errorMessage)
                    }
        })

    /*    allShopsInteractor.execute(
                success = { shops: Shops ->

                }, error = { msg: String ->

                })*/

        /*
        DeleteAllShopsImpl(this).execute(error = {

        }, success = {
            Log.d("Success", "success")
        })*/

    }

    override fun onLowMemory() {
        super.onLowMemory()

    }
}