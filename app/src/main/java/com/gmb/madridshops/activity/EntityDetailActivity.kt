package com.gmb.madridshops.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import com.gmb.madridshops.R
import com.gmb.madridshops.domain.model.Shop
import com.gmb.madridshops.fragment.EntityDetailFragment
import com.gmb.madridshops.util.EXTRA_SELECTED_ENTITY

class EntityDetailActivity : AppCompatActivity() {

    lateinit var containerListFragment: EntityDetailFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entity_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val intent = intent
        val entity = intent.getSerializableExtra(EXTRA_SELECTED_ENTITY) as Shop

        containerListFragment = supportFragmentManager.findFragmentById(R.id.activity_list_detail_fragment) as EntityDetailFragment
        containerListFragment.setEntity(entity)

        supportActionBar?.title = entity.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == android.R.id.home) {
            // se ha pulsado la flecha de back
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}
