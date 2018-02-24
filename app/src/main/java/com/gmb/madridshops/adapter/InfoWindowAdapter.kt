package com.gmb.madridshops.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.gmb.madridshops.R
import com.gmb.madridshops.domain.model.Entity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.marker_pin_info.view.*
import java.util.*


class InfoWindowAdapter(val context: Context) : GoogleMap.InfoWindowAdapter {

    override fun getInfoContents(m: Marker): View {

        val infoWinView = LayoutInflater.from(context).inflate(R.layout.marker_pin_info, null)
        val entity = m.tag as Entity
        val image = infoWinView.info_window_image

        infoWinView.info_window_title.text = entity.name

        when (Locale.getDefault().getLanguage()) {
            "es" -> {
                infoWinView.info_window_subtitle.text = entity.openingHours_es
            }
            else -> {
                infoWinView.info_window_subtitle.text = entity.openingHours_en
            }
        }

        Picasso.with(context)
                .load(entity.logo)
                .into(image, InfoWindowAdapterCallback(context, entity.logo, image, m))

        return infoWinView
    }

    override fun getInfoWindow(m: Marker): View? {
        return null
    }
}

class InfoWindowAdapterCallback(
        val context: Context,
        val url: String,
        val view: ImageView,
        val marker: Marker) : Callback {

    override fun onSuccess() {
        if (marker.isInfoWindowShown) {
            marker.hideInfoWindow()

            Picasso.with(context).load(url).into(view)
            marker.showInfoWindow()
        }
    }

    override fun onError() {
        Log.d("Picasso", "Picasso error loading marker image")
    }

}