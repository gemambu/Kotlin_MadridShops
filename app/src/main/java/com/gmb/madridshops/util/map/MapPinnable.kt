package com.gmb.madridshops.util.map

interface MapPinneable<T> {

    val latitude: Float
    val longitude: Float
    val name: String
    val id: Int
    val pinDescription: String
    val pinImageUrl: String
    val relatedModelObject: T
}