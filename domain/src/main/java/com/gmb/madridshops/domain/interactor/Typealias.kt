package com.gmb.madridshops.domain.interactor

import com.gmb.madridshops.domain.model.Entities


typealias SuccessClosure = (entities: Entities) -> Unit
typealias ErrorClosure = (msg: String) -> Unit
typealias Variant = Any
typealias CodeClosure = () -> Unit