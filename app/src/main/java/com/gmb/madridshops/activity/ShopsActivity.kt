package com.gmb.madridshops.activity

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.gmb.madridshops.R
import com.gmb.madridshops.adapter.RecyclerViewAdapter
import com.gmb.madridshops.domain.interactor.ErrorCompletion
import com.gmb.madridshops.domain.interactor.SuccessCompletion
import com.gmb.madridshops.domain.interactor.getallshops.GetAllShopsInteractor
import com.gmb.madridshops.domain.interactor.getallshops.GetAllShopsInteractorImpl
import com.gmb.madridshops.domain.model.Shop
import com.gmb.madridshops.domain.model.Shops
import com.gmb.madridshops.fragment.ListFragment
import com.gmb.madridshops.util.map.MapUtil
import com.gmb.madridshops.util.map.model.ShopPin
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class ShopsActivity : AppCompatActivity(), RecyclerViewAdapter.OnEntityClickListener {
    override fun onEntityClicked(position: Int, entity: Shop, view: View) {

    }

    lateinit var containerListFragment: ListFragment
    val DEFAULT_MADRID_LATIDUDE = 40.4167
    val DEFAULT_MADRID_LONGITUDE = -3.70325

    private var map: GoogleMap? = null
    private var list: Shops? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_entity_list)
        //setSupportActionBar(toolbar)

        Log.d("App", "onCreate ShopsActivity")

        setupMap()


    }

    private fun setupList() {
        containerListFragment = supportFragmentManager.findFragmentById(R.id.activity_main_list_fragment) as ListFragment
        containerListFragment.setEntities(list!!.shops)
    }

    private fun setupMap() {
        val getAllShopsInteractor: GetAllShopsInteractor = GetAllShopsInteractorImpl(this)
        getAllShopsInteractor.execute(object: SuccessCompletion<Shops>{
            override fun successCompletion(e: Shops) {
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

    private fun initializeMap(shops: Shops) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({
            Log.d("SUCCESS", "HABEMUS MAPA")

            MapUtil().centerMapInPosition(it, DEFAULT_MADRID_LATIDUDE, DEFAULT_MADRID_LONGITUDE)
            it.uiSettings.isRotateGesturesEnabled = false
            it.uiSettings.isZoomControlsEnabled = true
            showUserPosition(baseContext, it)
            map = it
            addAllPins(shops)
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

    private fun addAllPins(shops: Shops){

        val shopPins = ShopPin.shopPinsFromShops(shops)

        MapUtil().addPins(shopPins, map, this)

        map?.setOnInfoWindowClickListener(GoogleMap.OnInfoWindowClickListener { marker ->
            if (marker.tag == null || marker.tag !is Shop) {
                return@OnInfoWindowClickListener
            }
            val shop = marker.tag as Shop?
            Log.d("APP", "Show detail for shop: ${shop?.name}")
            //Router.navigateFromShopListActivityToShopDetailActivity(this@ShopListActivity, shop, 0)
        })

    }

    private fun addPin(map: GoogleMap, latitude: Double, longitude: Double, title: String){
        map.addMarker(MarkerOptions().position(LatLng(latitude, longitude)).title(title))
    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //Router().navigateFromMainActivityToPicassoActivity(this)

        /*return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }*/
        return true
    }

}