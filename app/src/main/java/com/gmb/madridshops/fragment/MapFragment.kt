package com.gmb.madridshops.fragment


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.gmb.madridshops.R
import com.gmb.madridshops.domain.model.Shops


/**
 * A simple [Fragment] subclass.
 */
class MapFragment : Fragment() {

    fun setShops(shops: Shops) {

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater!!.inflate(R.layout.fragment_map, container, false)
    }

}// Required empty public constructor
