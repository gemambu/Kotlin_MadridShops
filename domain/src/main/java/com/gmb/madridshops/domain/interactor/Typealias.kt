package com.gmb.madridshops.domain.interactor

import com.gmb.madridshops.domain.model.Shops

typealias SuccessClosure = (shops: Shops) -> Unit
typealias ErrorClosure = (msg: String) -> Unit
typealias Variant = Any
typealias CodeClosure = () -> Unit