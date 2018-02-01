package com.gmb.madridshops.domain.interactor.deleteallshops

import com.gmb.madridshops.domain.interactor.CodeClosure
import com.gmb.madridshops.domain.interactor.ErrorClosure

interface DeleteAllShops {
    fun execute(successCompletion: CodeClosure, errorCompletion: ErrorClosure)
}
