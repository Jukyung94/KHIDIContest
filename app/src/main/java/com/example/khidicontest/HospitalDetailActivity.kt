package com.example.khidicontest

import android.content.ContentValues.TAG
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationRequest
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.khidicontest.databinding.ActivityHospitalDetailBinding
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.*

class HospitalDetailActivity : AppCompatActivity(), OnMapReadyCallback{

    private lateinit var mMap: GoogleMap
//    internal lateinit var mLastLocation: Location
//    internal var mCurrentLocationMarker: Marker? = null
//    internal var mGoogleApiClient: GoogleApiClient? = null
//    internal lateinit var mLocationRequest: LocationRequest

    companion object {
        const val HOSPITALNAME = "hospital"
        const val HOSPITALADDRESS = "address"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityHospitalDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val hospitalName = intent?.extras?.getString(HOSPITALNAME)
        val hospitalAddress = intent?.extras?.getString(HOSPITALADDRESS)


        val title: TextView = findViewById(R.id.hospital_title)
        title.text = hospitalName

        val address: TextView = findViewById(R.id.hospital_address)
        address.text = hospitalAddress

        val button: Button = findViewById(R.id.search_on_google)
        button.setOnClickListener {
            searchOnGoogle()
        }

        val infoBtn: Button = findViewById(R.id.show_accommodation)
        infoBtn.setOnClickListener {
            val intent = Intent(this, AccommodationInfo::class.java)
            startActivity(intent)
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        Handler(Looper.getMainLooper()).postDelayed({
            val geocoder: Geocoder = Geocoder(this)
            try {
                println("2")
                var listAddress = geocoder.getFromLocationName(hospitalName.toString(), 1)
                if(listAddress?.size!! == 0) {
                    var listAddress = geocoder.getFromLocationName(hospitalName.toString().substring(0, hospitalName.toString().length.minus(2)), 1)
                    if(listAddress?.size!! > 0) {
                        val latlang: LatLng = LatLng(listAddress!!.get(0).latitude, listAddress!!.get(0).longitude)
                        if (mMap != null) {
                            mMap.clear()
                            mMap.addMarker(
                                MarkerOptions()
                                    .position(latlang)
                                    .title(hospitalName)
                            )
                            mMap.moveCamera(CameraUpdateFactory.newLatLng(latlang))
                            mMap.moveCamera(CameraUpdateFactory.zoomTo(13f))
                        }
                    } else {
                        Toast.makeText(this, "위치정보를 찾을 수 없습니다.", Toast.LENGTH_SHORT).show()
                        val seoul: LatLng = LatLng(37.5, 127.0)
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(seoul))
                        mMap.moveCamera(CameraUpdateFactory.zoomTo(10f))
                    }
                } else if(listAddress?.size!! > 0) {
                    val latlang: LatLng = LatLng(listAddress.get(0).latitude, listAddress.get(0).longitude)
                    if (mMap != null) {
                        mMap.clear()
                        mMap.addMarker(
                            MarkerOptions()
                                .position(latlang)
                                .title(hospitalName)
                        )
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latlang))
                        mMap.moveCamera(CameraUpdateFactory.zoomTo(13f))
                    }
                }
            } catch (e: IOException ) {
                println(e)
            }
        }, 500)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val default = LatLng(37.0, 127.0)
        mMap.moveCamera(CameraUpdateFactory.newLatLng(default))
    }
    private fun searchOnGoogle() {
        val queryUrl: Uri = Uri.parse("${SEARCH_PREFIX}${intent?.extras?.getString(HOSPITALNAME)}")
        val intent = Intent(Intent.ACTION_VIEW, queryUrl)
        startActivity(intent)
    }
}