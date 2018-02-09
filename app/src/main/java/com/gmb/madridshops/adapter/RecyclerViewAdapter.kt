package com.gmb.madridshops.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gmb.madridshops.R
import com.gmb.madridshops.domain.model.Shop
import com.squareup.picasso.Picasso


class RecyclerViewAdapter(private val listData: List<Shop>?, private val listener: OnEntityClickListener?) : RecyclerView.Adapter<RecyclerViewAdapter.EntityViewHolder>() {


    private var onEntityClickListener: OnEntityClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): EntityViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.content_entity, parent, false)
        onEntityClickListener = listener
        return EntityViewHolder(view)
    }

    override fun onBindViewHolder(holder: EntityViewHolder?, position: Int) {

        if (listData != null) {
            holder?.bindEntity(listData[position], position)
        }
    }

    override fun getItemCount() = listData?.size ?: 0

    inner class EntityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val name = itemView.findViewById<TextView>(R.id.name)
        private val address = itemView.findViewById<TextView>(R.id.address)
        private val logo = itemView.findViewById<ImageView>(R.id.logo)


        fun bindEntity(entity: Shop, position: Int) {

            // actualizamos la vista (itemView, que es la tarjeta) con el modelo
            name.text = entity.name
            //address.text = itemView.context.getString(R.string.dish_price, dish.price)
            address.text = entity.address
            //logo.setImageResource(getDishPhoto(dish.photo))
            Picasso
                    .with(super.itemView.context)
                    .load(entity.logo)
                    .into(logo)


            itemView.setOnClickListener {
                onEntityClickListener?.onEntityClicked(position, entity, itemView)
            }

        }

    }


    interface OnEntityClickListener {
        fun onEntityClicked(position: Int, entity: Shop, view: View)
    }
}