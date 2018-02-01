package com.gmb.madridshops.repository.network

import com.gmb.madridshops.repository.ErrorCompletion
import com.gmb.madridshops.repository.SuccessCompletion

internal interface GetJsonManager {
    fun execute(url: String, success: SuccessCompletion<String>, error: ErrorCompletion)
}