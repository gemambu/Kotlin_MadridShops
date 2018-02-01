package com.gmb.madridshops.domain.interactor.internetstatus

import com.gmb.madridshops.domain.interactor.CodeClosure
import com.gmb.madridshops.domain.interactor.ErrorClosure


class InternetStatusInteractorFakeImpl: InternetStatusInteractor {
    override fun execute(success: CodeClosure, error: ErrorClosure) {
        success()
    }
}