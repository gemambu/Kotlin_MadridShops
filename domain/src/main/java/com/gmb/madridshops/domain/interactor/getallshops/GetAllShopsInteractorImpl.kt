package com.gmb.madridshops.domain.interactor.getallshops

import android.content.Context
import com.gmb.madridshops.domain.interactor.ErrorCompletion
import com.gmb.madridshops.domain.interactor.SuccessCompletion
import com.gmb.madridshops.domain.model.Entities
import com.gmb.madridshops.domain.util.EntityType
import com.gmb.madridshops.domain.util.Mapper
import com.gmb.madridshops.repository.Repository
import com.gmb.madridshops.repository.RepositoryImplementation
import java.lang.ref.WeakReference

class GetAllShopsInteractorImpl(context: Context) : GetAllShopsInteractor {
    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImplementation(weakContext.get()!!)

    override fun execute(success: SuccessCompletion<Entities>, error: ErrorCompletion) {

        repository.getAllEntities(success = {
            val shops: Entities = Mapper().entitiesMapper(it, EntityType.SHOP)
            success.successCompletion(shops)
        }, error = {
            error(it)
        })
    }

}
