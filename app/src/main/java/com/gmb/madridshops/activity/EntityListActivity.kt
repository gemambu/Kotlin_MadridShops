package com.gmb.madridshops.activity

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.gmb.madridshops.R
import com.gmb.madridshops.adapter.InfoWindowAdapter
import com.gmb.madridshops.adapter.RecyclerViewAdapter
import com.gmb.madridshops.domain.interactor.ErrorCompletion
import com.gmb.madridshops.domain.interactor.SuccessCompletion
import com.gmb.madridshops.domain.interactor.getallentities.GetAllEntitiesInteractor
import com.gmb.madridshops.domain.interactor.getallentities.GetAllEntitiesInteractorImpl
import com.gmb.madridshops.domain.model.Entities
import com.gmb.madridshops.domain.model.Entity
import com.gmb.madridshops.domain.util.EntityType
import com.gmb.madridshops.fragment.EntityListFragment
import com.gmb.madridshops.router.Router
import com.gmb.madridshops.util.*
import com.gmb.madridshops.util.map.MapUtil
import com.gmb.madridshops.util.map.model.EntityPin
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment


class EntityListActivity : AppCompatActivity(), RecyclerViewAdapter.OnEntityClickListener {

    lateinit var containerListFragment: EntityListFragment

    private var map: GoogleMap? = null
    private var list: Entities? = null
    private lateinit var entityType: EntityType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_entity_list)

        Log.d(APP, "onCreate EntityListActivity")

        val intent = intent
        entityType = intent.getSerializableExtra(EXTRA_ENTITY_TYPE) as EntityType

        setupData()

        displayToolbar()

    }

    private fun displayToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        val title = findViewById<TextView>(R.id.toolbar_title)
        setSupportActionBar(toolbar)

        title.text = getTitleEntity()
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }


    private fun getTitleEntity(): String {

        return when (entityType) {
            EntityType.ACTIVITY -> getString(R.string.list_entity_activities)
            EntityType.SHOP -> getString(R.string.list_entity_shops)
        }
    }

    private fun setupData() {

        val getAllEntitiesInteractor: GetAllEntitiesInteractor = GetAllEntitiesInteractorImpl(this)
        getAllEntitiesInteractor.execute(entityType, object : SuccessCompletion<Entities> {

            override fun successCompletion(e: Entities) {
                list = e
                setupList()
                initializeMap(e)

            }

        }, object : ErrorCompletion {
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(
                        baseContext,
                        getString(R.string.error_loading_list),
                        Toast.LENGTH_LONG
                ).show()
            }

        })
    }

    private fun setupList() {
        containerListFragment = supportFragmentManager.findFragmentById(R.id.activity_main_list_fragment) as EntityListFragment
        containerListFragment.setEntities(list!!.entities)
    }

    private fun initializeMap(entities: Entities) {

        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as SupportMapFragment

        mapFragment.getMapAsync({
            Log.d(SUCCESS, getString(R.string.loaded_map_correctly))

            MapUtil().centerMapInPosition(it, DEFAULT_MADRID_LATIDUDE, DEFAULT_MADRID_LONGITUDE)

            map = it

            it.uiSettings.isRotateGesturesEnabled = false
            it.uiSettings.isZoomControlsEnabled = true

            showUserPosition(baseContext, it)

            addAllPins(entities)

            it.setInfoWindowAdapter(InfoWindowAdapter(this))
            it.setOnInfoWindowClickListener({
                val entity: Entity = it.tag as Entity
                Router().navigateFromListActivityToDetailActivity(this, entity)

            })

            it.setOnMarkerClickListener(object : GoogleMap.OnMarkerClickListener {
                override fun onMarkerClick(marker: com.google.android.gms.maps.model.Marker?): Boolean {

                    if (marker != null ){

                        val markerId = marker.snippet.toInt()
                        Log.d(APP, "Clicked on if: $markerId")
                        containerListFragment.displaySelectedItem(markerId)
                        return false
                    }

                    return true
                }
            })
        })
    }

    private fun showUserPosition(context: Context, map: GoogleMap?) {
        if (ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
                    10)
            return
        }
        map?.isMyLocationEnabled = true

    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10) {
            try {
                map?.isMyLocationEnabled = true
            } catch (e: SecurityException) {
            }
        }
    }

    private fun addAllPins(entities: Entities) {

        val pins = EntityPin.entityPins(entities)
        MapUtil().addPins(pins, map, this)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            // se ha pulsado la flecha de back
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onEntityClicked(position: Int, entity: Entity, view: View) {
        Router().navigateFromListActivityToDetailActivity(this, entity)
    }

}