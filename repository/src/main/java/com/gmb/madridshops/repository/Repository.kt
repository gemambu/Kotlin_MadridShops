package com.gmb.madridshops.repository

import com.gmb.madridshops.repository.model.EntityData

interface Repository {

    fun countEntities(): Int
    fun deleteAllEntities(success: () -> Unit, error: (errorMessage: String) -> Unit)
    fun getAllEntities(type: String, success: (entities: List<EntityData>) -> Unit, error: (errorMessage: String) -> Unit)
}