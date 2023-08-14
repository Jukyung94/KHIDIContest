package com.example.khidicontest

import android.content.Intent
import android.location.Geocoder
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.khidicontest.databinding.ActivityHospitalDetailBinding
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException
import java.util.*

class HospitalDetailActivity : AppCompatActivity(), OnMapReadyCallback {

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

        val mapFragment = SupportMapFragment.newInstance()
        supportFragmentManager
            .beginTransaction()
            .add(R.id.map, mapFragment)
            .commit()
        mapFragment.getMapAsync(this)

//        var googleMap: GoogleMap? = null
        val geocoder: Geocoder = Geocoder(applicationContext, Locale.getDefault())
//        try {
//            var listAddress = geocoder.getFromLocationName(hospitalName.toString(), 1)
//            if(listAddress?.size!! > 0) {
//                println("asdfsdf")
//                val latlang: LatLng = LatLng(listAddress.get(0).latitude, listAddress.get(0).longitude)
//                println(latlang)
////                if (googleMap != null) {
////                    googleMap.addMarker(
////                        MarkerOptions()
////                            .position(latlang)
////                            .title(hospitalName)
////                    )
////                }
//            }
//        } catch (e: IOException ) {
//            println(e)
//        }

    }

    override fun onMapReady(googleMap: GoogleMap) {
        googleMap.addMarker(
            MarkerOptions()
                .position(LatLng(0.0, 0.0))
                .title("Marker")
        )
    }

    private fun searchOnGoogle() {
        val queryUrl: Uri = Uri.parse("${SEARCH_PREFIX}${intent?.extras?.getString(HOSPITALNAME)}")
        val intent = Intent(Intent.ACTION_VIEW, queryUrl)
        startActivity(intent)
    }
}