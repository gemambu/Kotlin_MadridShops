package com.gmb.madridshops.domain.model


import java.io.Serializable

/**
 * Shop: Represents one shop
 */
open class Entity(val id: Int,
                  val name: String,
                  val address: String,
                  val description_en: String,
                  val description_es: String,
                  val latitude: Float = 40.3456f,
                  val longitude: Float = -3.45645f,
                  val image: String = "",
                  val logo: String = "",
                  val openingHours_en: String = "",
                  val openingHours_es: String = ""): Serializable {
    init{
        Entities(ArrayList())
    }
}
/**
 *
 */
class Entities(val entities: MutableList<Entity>): Aggregate<Entity>{
    override fun count() = entities.size

    override fun all() = entities

    override operator fun get(position: Int) = entities[position]


    override fun add(element: Entity) {
        entities.add(element)
    }

    override fun delete(position: Int) {
        entities.removeAt(position)
    }

    override fun delete(element: Entity) {
        entities.remove(element)
    }


}