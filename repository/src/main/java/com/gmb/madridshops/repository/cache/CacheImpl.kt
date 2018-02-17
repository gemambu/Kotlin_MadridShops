package com.gmb.madridshops.repository.cache

import android.content.Context
import com.gmb.madridshops.repository.db.DBHelper
import com.gmb.madridshops.repository.db.buildHelper
import com.gmb.madridshops.repository.db.dao.EntityDAO
import com.gmb.madridshops.repository.model.EntityData
import com.gmb.madridshops.repository.thread.DispatchOnMainThread
import java.lang.ref.WeakReference


internal class CacheImplementation(context: Context) : Cache {

    private val context = WeakReference<Context>(context)

    override fun getAllShops(success: (shops: List<EntityData>) -> Unit, error: (errorMessage: String) -> Unit) {

        Thread(Runnable {
            val shops = EntityDAO(cacheDBHelper()).query()

            if (shops.isNotEmpty()){
                success(shops)
            } else {
                error("No shops")
            }
        }).run()

    }

    override fun saveAllShops(shops: List<EntityData>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            try {
                shops.forEach { EntityDAO(cacheDBHelper()).insert(it, "shop") }


                DispatchOnMainThread(Runnable {
                    success()
                })
            } catch (ex: Exception) {
                DispatchOnMainThread(Runnable {
                  error("Error inserting shops: " + ex.message.toString())
                })
            }
        }).run()
    }

    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            val successDeleting = EntityDAO(cacheDBHelper()).deleteAll()

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