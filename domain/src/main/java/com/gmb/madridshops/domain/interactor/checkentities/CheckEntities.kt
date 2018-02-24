package com.gmb.madridshops.domain.interactor.checkentities

import com.gmb.madridshops.domain.interactor.CodeClosure
import com.gmb.madridshops.domain.interactor.ErrorClosure

interface CheckEntities {
//    fun execute(success: (total: Int) -> Unit, errorCompletion: ErrorClosure)

    fun execute(): Int
}