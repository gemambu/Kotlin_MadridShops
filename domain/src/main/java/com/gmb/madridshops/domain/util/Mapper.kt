package com.gmb.madridshops.domain.util

import android.util.Log
import com.gmb.madridshops.domain.model.Activity
import com.gmb.madridshops.domain.model.Entities
import com.gmb.madridshops.domain.model.Entity
import com.gmb.madridshops.domain.model.Shop
import com.gmb.madridshops.repository.model.EntityData
import java.io.Serializable

enum class EntityType(val type: Int) : Serializable {
    SHOP(1),
    ACTIVITY(2)
}

class Mapper {

    internal fun entitiesMapper(list: List<EntityData>, type: EntityType): Entities {

        val tempList = ArrayList<Entity>()

        when (type) {
            EntityType.SHOP -> {
                list.forEach {
                    tempList.add(mapShop(it))
                }
            }
            EntityType.ACTIVITY -> {
                list.forEach {
                    tempList.add(mapActivity(it))
                }
            }
        }

        return Entities(tempList)
    }

    private fun mapShop(entity: EntityData): Shop = Shop(
            entity.id.toInt(),
            entity.name,
            entity.address,
            entity.description_en,
            entity.description_es,
            parseStringToFloat(entity.latitude),
            parseStringToFloat(entity.longitude),
            entity.image,
            entity.logo,
            entity.openingHours_en,
            entity.openingHours_es,
            EntityType.SHOP)

    private fun mapActivity(entity: EntityData): Activity = Activity(
            entity.id.toInt(),
            entity.name,
            entity.address,
            entity.description_en,
            entity.description_es,
            parseStringToFloat(entity.latitude),
            parseStringToFloat(entity.longitude),
            entity.image,
            entity.logo,
            entity.openingHours_en,
            entity.openingHours_es,
            EntityType.ACTIVITY)

    private fun parseStringToFloat(data: String): Float {
        var coordinate = 0f

        val parsedString: String = data.replace(",", "").replace(" ", "")

        try {
            coordinate = if (parsedString.isNotEmpty()) parsedString.toFloat() else 0f
        } catch (e: Exception) {
            Log.d("PARSE ERROR", "Error parsing string to float: " + data)
        }

        return coordinate
    }


}
