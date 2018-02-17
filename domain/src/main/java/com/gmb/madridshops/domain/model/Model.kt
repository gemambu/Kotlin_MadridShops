package com.gmb.madridshops.domain.model

import com.gmb.madridshops.domain.util.EntityType

data class Shop(private val _id: Int,
                private val _name: String,
                private val _address: String,
                private val _description_en: String,
                private val _description_es: String,
                private val _latitude: Float,
                private val _longitude: Float,
                private val _image: String,
                private val _logo: String,
                private val _openingHours_en: String,
                private val _openingHours_es: String,
                val type: EntityType = EntityType.SHOP) : Entity(_id, _name, _address, _description_en, _description_es, _latitude, _longitude, _image, _logo, _openingHours_en, _openingHours_es) {
    

    override fun toString(): String {
        return type.toString() + ": " + super.toString()
    }
}

data class Activity(val _id: Int,
                val _name: String,
                val _address: String,
                val _description_en: String,
                val _description_es: String,
                val _latitude: Float,
                val _longitude: Float,
                val _image: String,
                val _logo: String,
                val _openingHours_en: String,
                val _openingHours_es: String,
                val type: EntityType = EntityType.ACTIVITY) : Entity(_id, _name, _address, _description_en, _description_es, _latitude, _longitude, _image, _logo, _openingHours_en, _openingHours_es) {

    override fun toString(): String {
        return type.toString() + ": " + super.toString()
    }
}