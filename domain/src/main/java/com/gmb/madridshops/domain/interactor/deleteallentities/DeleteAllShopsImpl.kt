package com.gmb.madridshops.domain.interactor.deleteallentities


import android.content.Context
import com.gmb.madridshops.domain.interactor.CodeClosure
import com.gmb.madridshops.domain.interactor.ErrorClosure
import com.gmb.madridshops.repository.RepositoryImplementation
import java.lang.ref.WeakReference

class DeleteAllShopsImpl(context: Context) : DeleteAllShops {
    private val weakContext = WeakReference<Context>(context)

    override fun execute(successCompletion: CodeClosure, errorCompletion: ErrorClosure) {
        val repository = RepositoryImplementation(weakContext.get()!!)
        repository.deleteAllEntities(successCompletion, errorCompletion)
    }

}