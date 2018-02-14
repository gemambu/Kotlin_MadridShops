@file:Suppress("DEPRECATION")

package com.gmb.madridshops.fragment


import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gmb.madridshops.R
import com.gmb.madridshops.adapter.RecyclerViewAdapter
import com.gmb.madridshops.domain.model.Shop


class EntityListFragment : Fragment() {


    private var listData: MutableList<Shop>? = null
    private var onEntityClickListener: RecyclerViewAdapter.OnEntityClickListener? = null
    private lateinit var entityListRecyclerView: RecyclerView
    private var adapter: RecyclerViewAdapter? = null
    private lateinit var root: View

    fun setEntities(entities:  MutableList<Shop>) {
        this.listData = entities

        adapter = RecyclerViewAdapter(listData, onEntityClickListener)
        entityListRecyclerView.adapter = adapter

        //setListenerToAdapter(adapter!!)
    }

    /* private fun setListenerToAdapter(adapter: RecyclerViewAdapter) {
         adapter.onClickListener = View.OnClickListener { v: View? ->
             val position = shopRecyclerView.getChildAdapterPosition(v)
             val shop = listData?.get(position)
             // Send order to navigate to activity container
             onShowShopDetail?.showShopDetail(shop!!)
         }
     }*/

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        if (inflater != null) {
            root = inflater.inflate(R.layout.fragment_list, container, false)
            entityListRecyclerView = root.findViewById(R.id.recycler_view_entity)
            entityListRecyclerView.layoutManager = GridLayoutManager(activity, 1)
            entityListRecyclerView.itemAnimator = DefaultItemAnimator()

        }

        return root

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        commonOnAttach(context)
    }

    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        commonOnAttach(activity)
    }

    private fun commonOnAttach(context: Context?) {
        // Aquí nos llaman cuando el fragment "se engancha" a la actividad, y por tanto ya pertence a ella
        // Lo que vamos a hacer es quedarnos con la referencia a esa actividad para cuando tengamos que avisarle de "cosas"
        if (context is RecyclerViewAdapter.OnEntityClickListener) {
            onEntityClickListener = context
        }
    }

    override fun onDetach() {
        super.onDetach()

        // Si la actividad se "desengancha" de este fragment ya no tiene sentido guardar una referencia a ella, ya no le vamos
        // a avisar de nada, lo ponemos a null
        onEntityClickListener = null
    }

}