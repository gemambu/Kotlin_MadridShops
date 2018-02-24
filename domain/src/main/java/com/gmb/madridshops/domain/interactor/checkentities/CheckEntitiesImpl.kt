package com.gmb.madridshops.domain.interactor.checkentities

import android.content.Context
import com.gmb.madridshops.domain.interactor.CodeClosure
import com.gmb.madridshops.domain.interactor.ErrorClosure
import com.gmb.madridshops.repository.RepositoryImplementation
import java.lang.ref.WeakReference

class CheckEntitiesImpl(context: Context) : CheckEntities {
    private val weakContext = WeakReference<Context>(context)

    /* override fun execute(success: (total: Int) -> Unit, errorCompletion: ErrorClosure) {
        val repository = RepositoryImplementation(weakContext.get()!!)
        repository.countEntities(success, errorCompletion)
    } */

    override fun execute(): Int {
        val repository = RepositoryImplementation(weakContext.get()!!)
        return repository.countEntities()
    }
}