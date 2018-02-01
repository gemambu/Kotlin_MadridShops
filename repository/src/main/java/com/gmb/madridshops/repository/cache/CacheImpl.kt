package com.gmb.madridshops.repository.cache

import android.content.Context
import com.gmb.madridshops.repository.db.DBHelper
import com.gmb.madridshops.repository.db.buildHelper
import com.gmb.madridshops.repository.db.dao.ShopDAO
import com.gmb.madridshops.repository.thread.DispatchOnMainThread
import java.lang.ref.WeakReference


internal class CacheImplementation(context: Context) : Cache {

    val context = WeakReference<Context>(context)

    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            var successDeleting = ShopDAO(cacheDBHelper()).deleteAll()

            DispatchOnMainThread(Runnable {
                if (successDeleting) {
                    success()
                } else {
                    error("Error deleting")
                }
            })

        }).run()

    }

    private fun cacheDBHelper(): DBHelper {
        // FIXME mover esto a config o algo asi (el nombre de la BD)
        return buildHelper(context.get()!!, "MadridShops.sqlite", 1)
    }
}