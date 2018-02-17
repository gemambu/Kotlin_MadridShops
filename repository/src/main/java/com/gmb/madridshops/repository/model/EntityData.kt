package com.gmb.madridshops.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
data class EntityData(
        val databaseId: Long,
        val id: Long,
        val name: String,
        val description_en: String,
        val description_es: String,
        @JsonProperty("gps_lat") val latitude: String,
        @JsonProperty("gps_lon") val longitude: String,
        @JsonProperty("img") val image: String,
        @JsonProperty("logo_img") val logo: String,
        @JsonProperty("opening_hours_en") val openingHours_en: String,
        @JsonProperty("opening_hours_es") val openingHours_es: String,
        val address: String,
        val type: String = ""
)