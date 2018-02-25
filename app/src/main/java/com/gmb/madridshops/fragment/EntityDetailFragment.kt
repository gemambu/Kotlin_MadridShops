package com.gmb.madridshops.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.method.ScrollingMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmb.madridshops.R
import com.gmb.madridshops.domain.model.Entity
import com.gmb.madridshops.util.getGoogleMapUrl
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_entity_detail.*
import java.util.*


class EntityDetailFragment : Fragment() {

    private lateinit var entityDetail: Entity

    fun setEntity(entity: Entity) {
        this.entityDetail = entity

        loadEntityData()
    }

    private fun loadEntityData() {

        val url = getGoogleMapUrl(entityDetail.latitude, entityDetail.longitude)
        Picasso.with(root.context)
                .load(url)
                .placeholder(R.drawable.google_maps)
                .into(entity_detail_map)

        //entity_detail_name.text = entityDetail.name.toUpperCase()

        when (Locale.getDefault().language) {
            "es" -> {
                entity_detail_desc.text = entityDetail.description_es
                entity_detail_hours.text = entityDetail.openingHours_es
            }
            else -> {
                entity_detail_desc.text = entityDetail.description_en
                entity_detail_hours.text = entityDetail.openingHours_en
            }

        }

        entity_detail_desc.movementMethod = ScrollingMovementMethod();
        entity_detail_hours.movementMethod = ScrollingMovementMethod();

        entity_detail_address.text = entityDetail.address
    }

    private lateinit var root: View

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_entity_detail, container, false)
        }

        return root

    }

}// Required empty public constructor
