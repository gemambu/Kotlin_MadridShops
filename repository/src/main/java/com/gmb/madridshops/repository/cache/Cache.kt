package com.gmb.madridshops.repository.cache

import com.gmb.madridshops.repository.model.EntityData

internal interface Cache {

    fun deleteAllEntities(success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun countEntities(): Int
    fun getAllEntities(type: String, success: (entities: List<EntityData>) -> Unit, error: (errorMessage: String) -> Unit)
    fun saveAllEntities(type: String, entities: List<EntityData>, success: () -> Unit, error: (errorMessage: String) -> Unit)

}