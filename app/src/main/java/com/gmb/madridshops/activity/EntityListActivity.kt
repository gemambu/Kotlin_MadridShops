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
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.gmb.madridshops.R
import com.gmb.madridshops.adapter.RecyclerViewAdapter
import com.gmb.madridshops.domain.interactor.ErrorCompletion
import com.gmb.madridshops.domain.interactor.SuccessCompletion
import com.gmb.madridshops.domain.interactor.getallshops.GetAllEntitiesInteractor
import com.gmb.madridshops.domain.interactor.getallshops.GetAllEntitiesInteractorImpl
import com.gmb.madridshops.domain.model.Entities
import com.gmb.madridshops.domain.model.Entity
import com.gmb.madridshops.domain.model.Shop
import com.gmb.madridshops.domain.util.EntityType
import com.gmb.madridshops.fragment.EntityListFragment
import com.gmb.madridshops.router.Router
import com.gmb.madridshops.util.DEFAULT_MADRID_LATIDUDE
import com.gmb.madridshops.util.DEFAULT_MADRID_LONGITUDE
import com.gmb.madridshops.util.EXTRA_ENTITY_TYPE
import com.gmb.madridshops.util.map.MapUtil
import com.gmb.madridshops.util.map.model.EntityPin
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment

class EntityListActivity : AppCompatActivity(), RecyclerViewAdapter.OnEntityClickListener {

    private lateinit var containerListFragment: EntityListFragment

    private var map: GoogleMap? = null
    private var list: Entities? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_entity_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        Log.d("App", "onCreate EntityListActivity")

        val intent = intent
        val entityType = intent.getSerializableExtra(EXTRA_ENTITY_TYPE) as EntityType

        setupData()

        supportActionBar?.title = entityType.toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    private fun setupData() {

        val getAllShopsInteractor: GetAllEntitiesInteractor = GetAllEntitiesInteractorImpl(this)

        getAllShopsInteractor.execute(EntityType.SHOP, object: SuccessCompletion<Entities>{

            override fun successCompletion(e: Entities) {
                list = e
                setupList()
                initializeMap(e)

            }

        }, object: ErrorCompletion{
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(baseContext, "Error loading", Toast.LENGTH_LONG).show()
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
            Log.d("SUCCESS", "HABEMUS MAPA")

            MapUtil().centerMapInPosition(it, DEFAULT_MADRID_LATIDUDE, DEFAULT_MADRID_LONGITUDE)
            it.uiSettings.isRotateGesturesEnabled = false
            it.uiSettings.isZoomControlsEnabled = true
            showUserPosition(baseContext, it)
            map = it
            addAllPins(entities)
        })

    }

    private fun showUserPosition(context: Context, map: GoogleMap?){
        if(ActivityCompat.checkSelfPermission(context, ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this,
                    arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),
                    10)
            return
        }

        map?.isMyLocationEnabled = true


    }



    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 10){
            try{
                map?.isMyLocationEnabled = true
            } catch (e: SecurityException){
            }
        }
    }

    private fun addAllPins(entities: Entities){

        val shopPins = EntityPin.entityPins(entities)

        MapUtil().addPins(shopPins, map, this)

        map?.setOnInfoWindowClickListener(GoogleMap.OnInfoWindowClickListener { marker ->
            if (marker.tag == null || marker.tag !is Shop) {
                return@OnInfoWindowClickListener
            }
            val shop = marker.tag as Shop?
            Log.d("APP", "Show detail for shop: ${shop?.name}")
            Router().navigateFromListActivityToDetailActivity(this, shop!!)
        })

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
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