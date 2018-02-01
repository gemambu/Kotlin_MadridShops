package com.gmb.madridshops

import android.support.multidex.MultiDexApplication
import android.util.Log
import com.gmb.madridshops.domain.interactor.deleteallshops.DeleteAllShopsImpl
import com.gmb.madridshops.domain.interactor.getallshops.GetAllShopsInteractorFakeImpl
import com.gmb.madridshops.domain.model.Shops

class MadridShopsApp : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        // init code application wide

        Log.d("App", "onCreate")

        val allShopsInteractor = GetAllShopsInteractorFakeImpl()

//        allShopsInteractor.execute(
//                success = object : SuccessCompletion<Shops> {
//                    override fun successCompletion(shops: Shops) {
//                        Log.d("Shops", "count: " + shops.count())
//                    }
//
//                }, error = object : ErrorCompletion {
//                    override fun errorCompletion(errorMessage: String) {
//
//                    }
//        })

        allShopsInteractor.execute(
                success = { shops: Shops ->

                }, error = { msg: String ->

                })

        DeleteAllShopsImpl(this).execute(error = {

        }, success = {
            Log.d("Success", "success")
        })

    }

    override fun onLowMemory() {
        super.onLowMemory()

        //
    }
}