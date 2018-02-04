package com.gmb.madridshops.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class ShopEntity(
        val databaseId: Long,
        val id: Long,
        val name: String,
        @JsonProperty("description_en") val description: String,
        @JsonProperty("gps_lat") val latitude: String,
        @JsonProperty("gps_lon") val longitude: String,
        @JsonProperty("img") val image: String,
        @JsonProperty("logo_img") val logo: String,
        @JsonProperty("opening_hours_en") val openingHours: String,
        val address: String
)