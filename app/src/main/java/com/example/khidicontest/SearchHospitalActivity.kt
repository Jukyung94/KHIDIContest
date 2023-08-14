package com.example.khidicontest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.khidicontest.adapter.SearchHospitalAdapter
import com.example.khidicontest.data.DataSource
import com.example.khidicontest.databinding.ActivitySearchHospitalBinding
import com.example.khidicontest.model.Hospital
import com.opencsv.CSVReader
import java.io.BufferedReader

class SearchHospitalActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchHospitalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchHospitalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hospitalFilter.adapter = SearchHospitalAdapter(
            applicationContext
        )

        val fr = BufferedReader(assets.open("hospital_list.csv").reader())

        fr.use {
            val reader = CSVReader(fr)
            println(reader)
            reader.use { r ->

                var line = r.readNext()

                println(line)

                while (line != null) {
                    var key: Int  = 0;

                    DataSource.hospitals.toMutableList().add(
                        Hospital(
                            R.drawable.ic_hospital,
                            line[2],
                            line[4],
                            line[5],
                            line[6],
                            line[7]
                        )
                    )
                    line = r.readNext()
                }
            }
        }


        binding.hospitalFilter.setHasFixedSize(true)
    }
}