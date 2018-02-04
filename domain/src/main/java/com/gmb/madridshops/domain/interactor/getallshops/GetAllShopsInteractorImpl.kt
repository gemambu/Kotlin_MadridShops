package com.gmb.madridshops.domain.interactor.getallshops

import android.content.Context
import com.gmb.madridshops.domain.interactor.ErrorCompletion
import com.gmb.madridshops.domain.interactor.SuccessCompletion
import com.gmb.madridshops.domain.model.Shop
import com.gmb.madridshops.domain.model.Shops
import com.gmb.madridshops.repository.Repository
import com.gmb.madridshops.repository.RepositoryImplementation
import com.gmb.madridshops.repository.model.ShopEntity
import java.lang.ref.WeakReference

class GetAllShopsInteractorImpl(context: Context): GetAllShopsInteractor {
    private val weakContext = WeakReference<Context>(context)
    private val repository: Repository = RepositoryImplementation(weakContext.get()!!)

    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {

        repository.getAllShops(success = {
            val shops: Shops = entityMapper(it)
            success.successCompletion(shops)
        }, error = {
            error(it)
        })
    }

    // FIXME pasar a otra clase externa y completar el mapeo inverso y añadir más daros a shop
    private fun entityMapper(list: List<ShopEntity>): Shops {

        val tempList = ArrayList<Shop>()

        list.forEach{
            var shopEntity = Shop(it.id.toInt(), it.name, it.address)
            tempList.add(shopEntity)
        }

        return Shops(tempList)
    }

}
