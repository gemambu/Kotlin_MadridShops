package com.gmb.madridshops.util.map

import android.content.Context
import android.util.Log
import com.gmb.madridshops.R
import com.gmb.madridshops.activity.EntityListActivity
import com.gmb.madridshops.domain.model.Entity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapUtil {


    fun centerMapInPosition(map: GoogleMap, latitude: Double, longitude: Double) {
        val coordinate = LatLng(latitude, longitude)

        val cameraPosition = CameraPosition.Builder()
                .target(coordinate)
                .zoom(15f)
                .build()

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }

    fun addPins(mapPinnables: List<MapPinneable<Entity>>?, googleMap: GoogleMap?, context: Context?) {
        if (mapPinnables == null || googleMap == null || context == null) {
            return
        }

        for (pinnable in mapPinnables) {

            if (pinnable.latitude == 0f && pinnable.longitude == 0f) {
                Log.d("WARNING", "The entity ${pinnable.name} has not coordinates, it cannot be displayed in the map")
            } else {
                val position = LatLng(pinnable.latitude.toDouble(), pinnable.longitude.toDouble())
                val icon = BitmapDescriptorFactory.fromResource(R.drawable.pin)
                val marker = MarkerOptions().position(position).title(pinnable.pinDescription).icon(icon).snippet(pinnable.id.toString())
                val m = googleMap.addMarker(marker)
                m.tag = pinnable.relatedModelObject
            }
        }
    }

}