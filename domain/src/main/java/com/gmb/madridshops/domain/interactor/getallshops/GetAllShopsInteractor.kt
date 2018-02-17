package com.gmb.madridshops.domain.interactor.getallshops

import com.gmb.madridshops.domain.interactor.ErrorCompletion
import com.gmb.madridshops.domain.interactor.SuccessCompletion
import com.gmb.madridshops.domain.model.Entities

interface GetAllShopsInteractor {
    fun execute(success: SuccessCompletion<Entities>, error: ErrorCompletion)
}