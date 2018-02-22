package com.gmb.madridshops.util.map.model

import com.gmb.madridshops.domain.model.Entities
import com.gmb.madridshops.domain.model.Entity
import com.gmb.madridshops.util.map.MapPinneable

class EntityPin(override val relatedModelObject: Entity) : MapPinneable<Entity> {

    override val latitude: Float
        get() = relatedModelObject.latitude

    override val longitude: Float
        get() = relatedModelObject.longitude

    override val pinDescription: String
        get() = relatedModelObject.name + " - " + relatedModelObject.address

    override val pinImageUrl: String
        get() = relatedModelObject.logo

    override val name: String
        get() = relatedModelObject.name

    companion object {

        fun entityPins(entities: Entities): List<MapPinneable<Entity>> {
            val entityList = entities.entities
            return entityList.map { EntityPin(it) }
        }
    }
}