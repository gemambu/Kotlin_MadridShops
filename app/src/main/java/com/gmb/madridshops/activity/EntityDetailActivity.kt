package com.gmb.madridshops.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.gmb.madridshops.R
import com.gmb.madridshops.domain.model.Shop
import com.gmb.madridshops.fragment.EntityDetailFragment
import com.gmb.madridshops.fragment.ListFragment

class EntityDetailActivity : AppCompatActivity() {

    lateinit var containerListFragment: EntityDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entity_detail)

        val intent = intent
        val entity = intent.getSerializableExtra("EXTRA_SELECTED_ENTITY") as Shop

        containerListFragment = supportFragmentManager.findFragmentById(R.id.activity_list_detail_fragment) as EntityDetailFragment
        containerListFragment.setEntity(entity)
    }
}
