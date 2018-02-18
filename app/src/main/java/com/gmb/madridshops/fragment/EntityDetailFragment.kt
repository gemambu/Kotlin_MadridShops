package com.gmb.madridshops.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmb.madridshops.R
import com.gmb.madridshops.domain.model.Shop
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_entity_detail.*


class EntityDetailFragment : Fragment() {

    private lateinit var entityDetail: Shop

    fun setEntity(entity:  Shop) {
        this.entityDetail = entity

        loadEntityData()

    }

    private fun loadEntityData() {

        val url = "https://maps.googleapis.com/maps/api/staticmap?center=${entityDetail.latitude},${entityDetail.longitude}&maptype=roadmap&format=png&zoom=17&size=320x220&scale=2&markers=%7Ccolor:0x9C7B14%7C${entityDetail.latitude},${entityDetail.longitude}"
        Picasso.with(root.context)
                .load(url)
                .placeholder(R.drawable.google_maps)
                .into(entity_detail_map)

        entity_detail_name.text = entityDetail.name.toUpperCase()
        entity_detail_desc.text = entityDetail.description_en
        entity_detail_hours.text = entityDetail.openingHours_en
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

//    override fun onAttach(context: Context?) {
//        super.onAttach(context)
//        commonOnAttach(context)
//    }
//
//    override fun onAttach(activity: Activity?) {
//        super.onAttach(activity)
//        commonOnAttach(activity)
//    }
//
//    private fun commonOnAttach(context: Context?) {
//        // Aquí nos llaman cuando el fragment "se engancha" a la actividad, y por tanto ya pertence a ella
//        // Lo que vamos a hacer es quedarnos con la referencia a esa actividad para cuando tengamos que avisarle de "cosas"
//        if (context is RecyclerViewAdapter.OnEntityClickListener) {
//            onEntityClickListener = context
//        }
//    }
//
//    override fun onDetach() {
//        super.onDetach()
//
//        // Si la actividad se "desengancha" de este fragment ya no tiene sentido guardar una referencia a ella, ya no le vamos
//        // a avisar de nada, lo ponemos a null
//        onEntityClickListener = null
//    }
}// Required empty public constructor
