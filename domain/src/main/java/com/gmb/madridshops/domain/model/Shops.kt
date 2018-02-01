package com.gmb.madridshops.domain.model

import java.util.ArrayList


/**
 * Shop: Represents one shop
 */
data class Shop(val id: Int, val name: String, val address: String){
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

    override fun get(position: Int) = shops[position]


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