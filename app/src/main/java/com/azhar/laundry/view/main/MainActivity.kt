package com.azhar.laundry.view.main

import android.Manifest
import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.RecyclerView
import com.azhar.laundry.R
import com.azhar.laundry.model.nearby.ModelResults
import com.azhar.laundry.viewmodel.MainViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import im.delight.android.location.SimpleLocation

class MainActivity : AppCompatActivity(), OnMapReadyCallback {
    var REQ_PERMISSION = 100
    var strCurrentLatitude = 0.0
    var strCurrentLongitude = 0.0
    var strCurrentLocation: String? = null
    var mapsView: GoogleMap? = null
    var simpleLocation: SimpleLocation? = null
    var progressDialog: ProgressDialog? = null
    var mainViewModel: MainViewModel? = null
    var menuAdapter: MenuAdapter? = null
    var mainAdapter: MainAdapter? = null
    var modelMenu: ModelMenu? = null
    var rvMenu: RecyclerView? = null
    var rvRekomendasi: RecyclerView? = null
    var layoutHistory: LinearLayout? = null
    var modelMenuList: MutableList<ModelMenu> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setStatusbar()
        setPermission()
        setLocation()
        setInitLayout()
        setMenu()
        locationViewModel
    }

    private fun setLocation() {
        simpleLocation = SimpleLocation(this)
        if (!simpleLocation!!.hasLocationEnabled()) {
            SimpleLocation.openSettings(this)
        }

        //get location
        strCurrentLatitude = simpleLocation!!.latitude
        strCurrentLongitude = simpleLocation!!.longitude

        //set location lat long
        strCurrentLocation = "$strCurrentLatitude,$strCurrentLongitude"
    }

    private fun setInitLayout() {
        rvMenu = findViewById(R.id.rvMenu)
        rvRekomendasi = findViewById(R.id.rvRekomendasi)
        layoutHistory = findViewById(R.id.layoutHistory)
        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("Mohon Tunggu…")
        progressDialog!!.setCancelable(false)
        progressDialog!!.setMessage("sedang menampilkan lokasi")
        mainAdapter = MainAdapter(this)
        rvRekomendasi?.run {
            setAdapter(mainAdapter)
            setHasFixedSize(true)
        }

    }

    private fun setPermission() {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQ_PERMISSION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (grantResult in grantResults) {
            if (grantResult == PackageManager.PERMISSION_GRANTED) {
                val intent = intent
                finish()
                startActivity(intent)
            }
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mapsView = googleMap

        //viewmodel
        locationViewModel
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ_PERMISSION && resultCode == RESULT_OK) {

            //load viewmodel
            locationViewModel
        }
    }

    private fun setStatusbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false)
            window.statusBarColor = Color.TRANSPARENT
        }
    }

    private fun setMenu() {
        modelMenu = ModelMenu("Cuci Basah", R.drawable.ic_cuci_basah)
        modelMenuList.add(modelMenu!!)
        modelMenu = ModelMenu("Dry Cleaning", R.drawable.ic_dry_cleaning)
        modelMenuList.add(modelMenu!!)
        modelMenu = ModelMenu("Premium Wash", R.drawable.ic_premium_wash)
        modelMenuList.add(modelMenu!!)
        modelMenu = ModelMenu("Setrika", R.drawable.ic_setrika)
        modelMenuList.add(modelMenu!!)
        menuAdapter = MenuAdapter(this, modelMenuList)
        rvMenu!!.adapter = menuAdapter
    }

    private val locationViewModel: Unit
        private get() {
            mainViewModel = ViewModelProvider(this, NewInstanceFactory()).get(
                MainViewModel::class.java
            )
            mainViewModel!!.setMarkerLocation(strCurrentLocation)
            progressDialog!!.show()
            mainViewModel!!.markerLocation.observe(this) { modelResults: ArrayList<ModelResults> ->
                if (modelResults.size != 0) {
                    mainAdapter!!.setLocationAdapter(modelResults)
                    progressDialog!!.dismiss()
                } else {
                    progressDialog!!.show()
                }
                progressDialog!!.dismiss()
            }
        }

    companion object {
        fun setWindowFlag(activity: Activity, bits: Int, on: Boolean) {
            val window = activity.window
            val layoutParams = window.attributes
            if (on) {
                layoutParams.flags = layoutParams.flags or bits
            } else {
                layoutParams.flags = layoutParams.flags and bits.inv()
            }
            window.attributes = layoutParams
        }
    }

}