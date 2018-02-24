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
    private val dbHelper = cacheDBHelper()

    override fun getAllEntities(type: String, success: (entities: List<EntityData>) -> Unit, error: (errorMessage: String) -> Unit) {

        Thread(Runnable {
            val entityList = EntityDAO(dbHelper).query(type)

            if (entityList.isNotEmpty()) {
                //dbHelper.close()
                success(entityList)
            } else {
                //dbHelper.close()
                error("Error getting $type list")
            }
            dbHelper.close()
        }).run()

    }

    override fun saveAllEntities(type: String, entities: List<EntityData>, success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            try {
                entities.forEach { EntityDAO(dbHelper).insert(it, type) }

                DispatchOnMainThread(Runnable {
                    dbHelper.close()
                    success()
                })
            } catch (ex: Exception) {
                DispatchOnMainThread(Runnable {
                    error("Error inserting entities: " + ex.message.toString())
                    dbHelper.close()
                })
            }
        }).run()
    }

    override fun deleteAllEntities(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        Thread(Runnable {
            val successDeleting = EntityDAO(dbHelper).deleteAll()

            DispatchOnMainThread(Runnable {
                if (successDeleting) {
                    //dbHelper.close()
                    success()

                } else {
                    //dbHelper.close()
                    error("Error deleting")
                }
                dbHelper.close()
            })

        }).run()

    }

    private fun cacheDBHelper(): DBHelper {
        // FIXME mover esto a config o algo asi (el nombre de la BD)
        return buildHelper(context.get()!!, "MadridShops.sqlite", 1)
    }
}