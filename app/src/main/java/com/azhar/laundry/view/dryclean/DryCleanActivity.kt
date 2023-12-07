package com.azhar.laundry.view.dryclean

import android.app.Activity
import android.graphics.Color
import android.location.Geocoder
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.azhar.laundry.R
import com.azhar.laundry.utils.FunctionHelper.rupiahFormat
import com.azhar.laundry.viewmodel.AddDataViewModel
import im.delight.android.location.SimpleLocation
import java.io.IOException
import java.util.Locale

class DryCleanActivity : AppCompatActivity() {
    var hargaKaos = 8000
    var hargaCelana = 6000
    var hargaJaket = 9000
    var hargaSprei = 65000
    var hargaKarpet = 200000
    var itemCount1 = 0
    var itemCount2 = 0
    var itemCount3 = 0
    var itemCount4 = 0
    var itemCount5 = 0
    var countKaos = 0
    var countCelana = 0
    var countJaket = 0
    var countSprei = 0
    var countKarpet = 0
    var totalItems = 0
    var totalPrice = 0
    var strTitle: String? = null
    var strCurrentLocation: String? = null
    var strCurrentLatLong: String? = null
    var strCurrentLatitude = 0.0
    var strCurrentLongitude = 0.0
    var simpleLocation: SimpleLocation? = null
    var addDataViewModel: AddDataViewModel? = null
    var btnCheckout: Button? = null
    var imageAdd1: ImageView? = null
    var imageAdd2: ImageView? = null
    var imageAdd3: ImageView? = null
    var imageAdd4: ImageView? = null
    var imageAdd5: ImageView? = null
    var imageMinus1: ImageView? = null
    var imageMinus2: ImageView? = null
    var imageMinus3: ImageView? = null
    var imageMinus4: ImageView? = null
    var imageMinus5: ImageView? = null
    var tvTitle: TextView? = null
    var tvInfo: TextView? = null
    var tvJumlahBarang: TextView? = null
    var tvTotalPrice: TextView? = null
    var tvKaos: TextView? = null
    var tvCelana: TextView? = null
    var tvJaket: TextView? = null
    var tvSprei: TextView? = null
    var tvKarpet: TextView? = null
    var tvPriceKaos: TextView? = null
    var tvPriceCelana: TextView? = null
    var tvPriceJaket: TextView? = null
    var tvPriceSprei: TextView? = null
    var tvPriceKarpet: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laundry)
        setLocation()
        setStatusbar()
        setInitLayout()
        setDataKaos()
        setDataCelana()
        setDataJaket()
        setDataSprei()
        setDataKarpet()
        setInputData()
        currentLocation
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
        strCurrentLatLong = "$strCurrentLatitude,$strCurrentLongitude"
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

    private fun setInitLayout() {
        tvTitle = findViewById(R.id.tvTitle)
        tvInfo = findViewById(R.id.tvInfo)
        tvJumlahBarang = findViewById(R.id.tvJumlahBarang)
        tvTotalPrice = findViewById(R.id.tvTotalPrice)
        tvKaos = findViewById(R.id.tvKaos)
        tvCelana = findViewById(R.id.tvCelana)
        tvJaket = findViewById(R.id.tvJaket)
        tvSprei = findViewById(R.id.tvSprei)
        tvKarpet = findViewById(R.id.tvKarpet)
        tvPriceKaos = findViewById(R.id.tvPriceKaos)
        tvPriceCelana = findViewById(R.id.tvPriceCelana)
        tvPriceJaket = findViewById(R.id.tvPriceJaket)
        tvPriceSprei = findViewById(R.id.tvPriceSprei)
        tvPriceKarpet = findViewById(R.id.tvPriceKarpet)
        imageAdd1 = findViewById(R.id.imageAdd1)
        imageAdd2 = findViewById(R.id.imageAdd2)
        imageAdd3 = findViewById(R.id.imageAdd3)
        imageAdd4 = findViewById(R.id.imageAdd4)
        imageAdd5 = findViewById(R.id.imageAdd5)
        imageMinus1 = findViewById(R.id.imageMinus1)
        imageMinus2 = findViewById(R.id.imageMinus2)
        imageMinus3 = findViewById(R.id.imageMinus3)
        imageMinus4 = findViewById(R.id.imageMinus4)
        imageMinus5 = findViewById(R.id.imageMinus5)
        btnCheckout = findViewById(R.id.btnCheckout)
        strTitle = intent.extras!!.getString(DATA_TITLE)
        if (strTitle != null) {
            tvTitle?.run { setText(strTitle) }
        }
        addDataViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory
                .getInstance(this.application)
        )
            .get(AddDataViewModel::class.java)
        val run = tvJumlahBarang?.run { setText("0 items") }
        tvTotalPrice?.run { setText("Rp 0") }
        tvInfo?.run { setText("Cuci kering adalah proses pencucian pakaian menggunakan bahan kimia dan teknik tertentu tanpa air.") }
    }

    private fun setDataKaos() {
        tvKaos!!.text = rupiahFormat(hargaKaos)
        imageAdd1!!.setOnClickListener { v: View? ->
            itemCount1 = itemCount1 + 1
            tvPriceKaos!!.setText(itemCount1)
            countKaos = hargaKaos * itemCount1
            setTotalPrice()
        }
        imageMinus1!!.setOnClickListener { v: View? ->
            if (itemCount1 > 0) {
                itemCount1 = itemCount1 - 1
                tvPriceKaos!!.setText(itemCount1)
            }
            countKaos = hargaKaos * itemCount1
            setTotalPrice()
        }
    }

    private fun setDataCelana() {
        tvCelana!!.text = rupiahFormat(hargaCelana)
        imageAdd2!!.setOnClickListener { v: View? ->
            itemCount2 = itemCount2 + 1
            tvPriceCelana!!.setText(itemCount2)
            countCelana = hargaCelana * itemCount2
            setTotalPrice()
        }
        imageMinus2!!.setOnClickListener { v: View? ->
            if (itemCount2 > 0) {
                itemCount2 = itemCount2 - 1
                tvPriceCelana!!.setText(itemCount2)
            }
            countCelana = hargaCelana * itemCount2
            setTotalPrice()
        }
    }

    private fun setDataJaket() {
        tvJaket!!.text = rupiahFormat(hargaJaket)
        imageAdd3!!.setOnClickListener { v: View? ->
            itemCount3 = itemCount3 + 1
            tvPriceJaket!!.setText(itemCount3)
            countJaket = hargaJaket * itemCount3
            setTotalPrice()
        }
        imageMinus3!!.setOnClickListener { v: View? ->
            if (itemCount3 > 0) {
                itemCount3 = itemCount3 - 1
                tvPriceJaket!!.setText(itemCount3)
            }
            countJaket = hargaJaket * itemCount3
            setTotalPrice()
        }
    }

    private fun setDataSprei() {
        tvSprei!!.text = rupiahFormat(hargaSprei)
        imageAdd4!!.setOnClickListener { v: View? ->
            itemCount4 = itemCount4 + 1
            tvPriceSprei!!.setText(itemCount4)
            countSprei = hargaSprei * itemCount4
            setTotalPrice()
        }
        imageMinus4!!.setOnClickListener { v: View? ->
            if (itemCount4 > 0) {
                itemCount4 = itemCount4 - 1
                tvPriceSprei!!.setText(itemCount4)
            }
            countSprei = hargaSprei * itemCount4
            setTotalPrice()
        }
    }

    private fun setDataKarpet() {
        tvKarpet!!.text = rupiahFormat(hargaKarpet)
        imageAdd5!!.setOnClickListener { v: View? ->
            itemCount5 = itemCount5 + 1
            tvPriceKarpet!!.setText(itemCount5)
            countKarpet = hargaKarpet * itemCount5
            setTotalPrice()
        }
        imageMinus5!!.setOnClickListener { v: View? ->
            if (itemCount5 > 0) {
                itemCount5 = itemCount5 - 1
                tvPriceKarpet!!.setText(itemCount5)
            }
            countKarpet = hargaKarpet * itemCount5
            setTotalPrice()
        }
    }

    private fun setTotalPrice() {
        totalItems = itemCount1 + itemCount2 + itemCount3 + itemCount4 + itemCount5
        totalPrice = countKaos + countCelana + countJaket + countSprei + countKarpet
        tvJumlahBarang!!.text = "$totalItems items"
        tvTotalPrice!!.text = rupiahFormat(totalPrice)
    }

    private val currentLocation: Unit
        private get() {
            val geocoder = Geocoder(this, Locale.getDefault())
            try {
                val addressList =
                    geocoder.getFromLocation(strCurrentLatitude, strCurrentLongitude, 1)
                if (addressList != null && addressList.size > 0) {
                    strCurrentLocation = addressList[0].locality
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    private fun setInputData() {
        btnCheckout!!.setOnClickListener { v: View? ->
            if (totalItems == 0 || totalPrice == 0) {
                Toast.makeText(
                    this@DryCleanActivity,
                    "Harap pilih jenis barang!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                addDataViewModel!!.addDataLaundry(
                    strTitle,
                    totalItems,
                    strCurrentLocation,
                    totalPrice
                )
                Toast.makeText(
                    this@DryCleanActivity,
                    "Pesanan Anda sedang diproses, cek di menu riwayat",
                    Toast.LENGTH_SHORT
                ).show()
                finish()
            }
        }
    }

    companion object {
        const val DATA_TITLE = "TITLE"
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