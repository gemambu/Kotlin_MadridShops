package com.gmb.madridshops.repository

import android.content.Context
import com.gmb.madridshops.repository.cache.Cache
import com.gmb.madridshops.repository.cache.CacheImplementation
import java.lang.ref.WeakReference

class RepositoryImplementation(context: Context): Repository {

    val weakContext = WeakReference<Context>(context)

    override fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit) {
        val cache: Cache = CacheImplementation(weakContext.get()!!)

        cache.deleteAllShops(success, error)
    }

}