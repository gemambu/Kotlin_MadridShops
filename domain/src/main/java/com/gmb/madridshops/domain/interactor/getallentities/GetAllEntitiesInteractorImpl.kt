package com.gmb.madridshops.domain.interactor.getallentities

import android.content.Context
import com.gmb.madridshops.domain.interactor.ErrorCompletion
import com.gmb.madridshops.domain.interactor.SuccessCompletion
import com.gmb.madridshops.domain.model.Entities
import com.gmb.madridshops.domain.util.EntityType
import com.gmb.madridshops.domain.util.Mapper
import com.gmb.madridshops.repository.Repository
import com.gmb.madridshops.repository.RepositoryImplementation
import java.lang.ref.WeakReference

class GetAllEntitiesInteractorImpl(context: Context) : GetAllEntitiesInteractor {
    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImplementation(weakContext.get()!!)


    override fun execute(entityType: EntityType, success: SuccessCompletion<Entities>, error: ErrorCompletion) {

        repository.getAllEntities(entityType.name, success = {
            val entities: Entities = Mapper().entitiesMapper(it, entityType)
            success.successCompletion(entities)
        }, error = {
            error(it)
        })
    }

}
