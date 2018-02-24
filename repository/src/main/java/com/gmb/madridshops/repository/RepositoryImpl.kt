package com.gmb.madridshops.repository

import android.content.Context
import android.util.Log
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.gmb.madridshops.repository.cache.Cache
import com.gmb.madridshops.repository.cache.CacheImplementation
import com.gmb.madridshops.repository.model.EntityData
import com.gmb.madridshops.repository.model.ResponseEntity
import com.gmb.madridshops.repository.network.GetJsonManager
import com.gmb.madridshops.repository.network.GetJsonManagerVolleyImpl
import com.gmb.madridshops.repository.network.json.JsonEntitiesParser
import java.lang.ref.WeakReference

class RepositoryImplementation(context: Context) : Repository {



    private val weakContext = WeakReference<Context>(context)
    private val cache: Cache = CacheImplementation(weakContext.get()!!)

    /*
    override fun countEntities(success: (total: Int) -> Unit, error: (errorMessage: String) -> Unit) {
       cache.countEntities(success = {

           Log.d("Holi", "¿Qué hago aquiii?")
          }, error = {
           Log.d("Holi", "¿Qué hago aquiii? pero con ERROR")
       })
    }*/

    override fun countEntities(): Int {
        return cache.countEntities()
    }

    override fun getAllEntities(type: String, success: (entities: List<EntityData>) -> Unit, error: (errorMessage: String) -> Unit) {

        cache.deleteAllEntities(success = {
            // Read all entities from cache
            cache.getAllEntities(type,
                    success = {
                        // if there are entities in the cache, return them
                        success(it)
                    }, error = {
                        // if no shops in cache --> network
                        populateCache(type, success, error)
                    })
        }, error = { Log.d("ERROR", "error borrando datos")})



    }

    private fun populateCache(type: String, success: (entities: List<EntityData>) -> Unit, error: (errorMessage: String) -> Unit) {
        // perform network request

        var url = ""

        when (type) {
            "SHOP" -> url = BuildConfig.MADRID_SHOPS_SERVER_URL
            "ACTIVITY" -> url = BuildConfig.MADRID_ACTIVITIES_SERVER_URL
        }

        val jsonManager: GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get()!!)
        jsonManager.execute(url, success = object : SuccessCompletion<String> {
            override fun successCompletion(e: String) {

                val parser = JsonEntitiesParser()
                val responseEntity: ResponseEntity

                try {
                    responseEntity = parser.parse(e)
                } catch (e: InvalidFormatException) {
                    error("Error parsing")
                    return
                }


                cache.saveAllEntities(type, responseEntity.result, success = {
                    success(responseEntity.result)
                }, error = {
                    error("Something happened on the way to heaven!")
                })

            }
        }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
            }
        })
    }


    override fun deleteAllEntities(success: () -> Unit, error: (errorMessage: String) -> Unit) = cache.deleteAllEntities(success, error)

}