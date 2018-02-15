package com.gmb.madridshops.domain.util

import android.util.Log
import com.gmb.madridshops.domain.model.Shop
import com.gmb.madridshops.repository.model.ShopEntity

class Mapper {

    fun map(entity: ShopEntity): Shop = Shop(entity.id.toInt(),
                                    entity.name,
                                    entity.address,
                                    entity.description_en,
                                    entity.description_es,
                                    parseStringToFloat(entity.latitude),
                                    parseStringToFloat(entity.longitude),
                                    entity.image,
                                    entity.logo,
                                    entity.openingHours_en,
                                    entity.openingHours_es)

    private fun parseStringToFloat(data: String): Float{
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
