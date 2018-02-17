package com.gmb.madridshops.repository.cache

import com.gmb.madridshops.repository.model.EntityData

internal interface Cache {

    fun deleteAllShops(success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllShops(success: (shops: List<EntityData>) -> Unit, error: (errorMessage: String) -> Unit)
    fun saveAllShops(shops: List<EntityData>, success: () -> Unit, error: (errorMessage: String) -> Unit)

}