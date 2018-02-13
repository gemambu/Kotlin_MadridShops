package com.gmb.madridshops.domain.model

import java.io.Serializable
import java.util.ArrayList


/**
 * Shop: Represents one shop
 */
data class Shop(val id: Int,
                val name: String,
                val address: String,
                val description_es: String,
                val description_en: String,
                val latitude: Float = 40.3456f,
                val longitude: Float = -3.45645f,
                val image: String = "",
                val logo: String = "",
                val openingHours_en: String = "",
                val openingHours_es: String = ""): Serializable {
    init{
        Shops(ArrayList<Shop>())
    }
}

/**
 *
 */
 class Shops(val shops: MutableList<Shop>): Aggregate<Shop>{
    override fun count() = shops.size

    override fun all() = shops

    override operator fun get(position: Int) = shops[position]


    override fun add(element: Shop) {
        shops.add(element)
    }

    override fun delete(position: Int) {
        shops.removeAt(position)
    }

    override fun delete(element: Shop) {
        shops.remove(element)
    }


}