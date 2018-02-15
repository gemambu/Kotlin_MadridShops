package com.gmb.madridshops.domain.interactor.getallshops

import com.gmb.madridshops.domain.interactor.ErrorClosure
import com.gmb.madridshops.domain.interactor.ErrorCompletion
import com.gmb.madridshops.domain.interactor.SuccessClosure
import com.gmb.madridshops.domain.interactor.SuccessCompletion
import com.gmb.madridshops.domain.model.Shop
import com.gmb.madridshops.domain.model.Shops

class GetAllShopsInteractorFakeImpl : GetAllShopsInteractor {
    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        val allOk = false

        // connect to the repo

        if (allOk) {
            success.successCompletion(createFakeShops())
        } else {
            error.errorCompletion("Error while accessing into the repo")
        }
    }

    fun execute(success: SuccessClosure, error: ErrorClosure){
        val allOk = false

        // connect to the repo

        if (allOk) {
            val shops = createFakeShops()
            success(shops)
        } else {
            error("Error while accessing into the repo")
        }
    }

    private fun createFakeShops(): Shops {

        val list = ArrayList<Shop>()

        (0..100).mapTo(list) { Shop(it, "Shop $it", "Address: $it", "desc_es", "desc_en") }

        return Shops(list)
    }

}
