package com.gmb.madridshops.util.map

import android.content.Context
import com.gmb.madridshops.R
import com.gmb.madridshops.R.id.img1
import com.gmb.madridshops.domain.model.Shop
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_picasso.*
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import java.net.URL


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