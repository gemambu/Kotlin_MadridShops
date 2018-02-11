package com.gmb.madridshops.util.map

import android.content.Context
import com.gmb.madridshops.domain.model.Shop
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapUtil {


    fun centerMapInPosition(map: GoogleMap, latitude: Double, longitude: Double){
        val coordinate = LatLng(latitude, longitude)

        val cameraPosition = CameraPosition.Builder()
                .target(coordinate)
                .zoom(15f)
                .build()

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    fun addPins(mapPinnables: List<MapPinneable<Shop>>?, googleMap: GoogleMap?, context: Context?) {
        if (mapPinnables == null || googleMap == null || context == null) {
            return
        }

        for (pinnable in mapPinnables) {
            val position = LatLng(pinnable.latitude.toDouble(), pinnable.longitude.toDouble())
            val marker = MarkerOptions().position(position).title(pinnable.pinDescription)
            val m = googleMap.addMarker(marker)
            m.tag = pinnable.relatedModelObject
        }
    }
}