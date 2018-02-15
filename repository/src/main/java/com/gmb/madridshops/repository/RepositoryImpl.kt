package com.gmb.madridshops.repository

import android.content.Context
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import com.gmb.madridshops.repository.cache.Cache
import com.gmb.madridshops.repository.cache.CacheImplementation
import com.gmb.madridshops.repository.model.ShopEntity
import com.gmb.madridshops.repository.model.ShopsResponseEntity
import com.gmb.madridshops.repository.network.GetJsonManager
import com.gmb.madridshops.repository.network.GetJsonManagerVolleyImpl
import com.gmb.madridshops.repository.network.json.JsonEntitiesParser
import java.lang.ref.WeakReference

class RepositoryImplementation(context: Context) : Repository {

    private val weakContext = WeakReference<Context>(context)
    private val cache: Cache = CacheImplementation(weakContext.get()!!)

    override fun getAllShops(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {

        // Read all shops from cache
        cache.getAllShops(
                success = {
                    // if there are shops in the cache, return them
                    success(it)
                }, error = {
                    // if no shops in cache --> network
                    populateCache(success, error)
                })

    }

    private fun populateCache(success: (shops: List<ShopEntity>) -> Unit, error: (errorMessage: String) -> Unit) {
        // perform network request

        val jsonManager: GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get()!!)
        jsonManager.execute(BuildConfig.MADRID_SHOPS_SERVER_URL, success = object : SuccessCompletion<String> {
            override fun successCompletion(e: String) {
                val parser = JsonEntitiesParser()
                val responseEntity: ShopsResponseEntity
                try {
                    responseEntity = parser.parse(e)
                } catch (e: InvalidFormatException) {
                    error("Error parsing")
                    return
                }
                cache.deleteAllShops(success = {
                    // store result in cache
                    cache.saveAllShops(responseEntity.result, success = {
                        success(responseEntity.result)
                    }, error = {
                        error("Something happened on the way to heaven!")
                    })
                }, error = {error("something happend deleting all the info")})

            }
        }, error = object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
            }
        })
    }


    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) = cache.deleteAllShops(success, error)


}