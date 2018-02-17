package com.gmb.madridshops.repository

import com.gmb.madridshops.repository.model.EntityData

interface Repository {

    fun deleteAllEntities(success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllEntities(success: (entities: List<EntityData>) -> Unit, error: (errorMessage: String) -> Unit)
    fun getEntitiesByType(type: String, success: (entities: List<EntityData>) -> Unit, error: (errorMessage: String) -> Unit)
}