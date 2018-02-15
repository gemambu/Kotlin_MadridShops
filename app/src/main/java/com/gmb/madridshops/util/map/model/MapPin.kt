package com.gmb.madridshops.util.map.model

import com.gmb.madridshops.domain.model.Shop
import com.gmb.madridshops.domain.model.Shops
import com.gmb.madridshops.util.map.MapPinneable
import java.util.ArrayList

class ShopPin(override val relatedModelObject: Shop) : MapPinneable<Shop> {

    override val latitude: Float
        get() = relatedModelObject.latitude

    override val longitude: Float
        get() = relatedModelObject.longitude

    override val pinDescription: String
        get() = relatedModelObject.name+ " - " + relatedModelObject.address

    override val pinImageUrl: String
        get() = relatedModelObject.logo

    companion object {

        fun shopPinsFromShops(shops: Shops): List<MapPinneable<Shop>> {
            val shopList = shops.shops
            val shopPinList = shopList.map { ShopPin(it) }

            return shopPinList
        }
    }
}