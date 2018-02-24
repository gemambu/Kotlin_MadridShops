package com.gmb.madridshops.domain.interactor.getallentities

import com.gmb.madridshops.domain.interactor.ErrorCompletion
import com.gmb.madridshops.domain.interactor.SuccessCompletion
import com.gmb.madridshops.domain.model.Entities
import com.gmb.madridshops.domain.util.EntityType

interface GetAllEntitiesInteractor {
    fun execute(entityType: EntityType, success: SuccessCompletion<Entities>, error: ErrorCompletion)
}