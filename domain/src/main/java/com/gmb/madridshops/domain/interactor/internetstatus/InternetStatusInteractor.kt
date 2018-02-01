package com.gmb.madridshops.domain.interactor.internetstatus

import com.gmb.madridshops.domain.interactor.CodeClosure
import com.gmb.madridshops.domain.interactor.ErrorClosure

interface InternetStatusInteractor {
    fun execute(success: CodeClosure, error: ErrorClosure)
}