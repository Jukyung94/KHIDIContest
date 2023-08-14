package com.example.khidicontest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import com.example.khidicontest.adapter.HospitalListAdapter
import com.example.khidicontest.data.DataSource
import com.example.khidicontest.databinding.ActivityHospitalListBinding
import com.example.khidicontest.model.Hospital
import com.opencsv.CSVReader
import java.io.BufferedReader

class HospitalListActivity : AppCompatActivity()  {
    companion object {
        const val DEPARTMENT = "department"
    }

    private lateinit var binding: ActivityHospitalListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHospitalListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val text: TextView = findViewById(R.id.filter_title)
        val NOH: TextView = findViewById(R.id.number_of_hospital)
        val filterName = intent?.extras?.getString(DEPARTMENT).toString()
        text.text = filterName

        val spinner: Spinner = findViewById(R.id.location_spinner)
        val fr = BufferedReader(assets.open("hospital_list.csv").reader())

        fr.use {
            val reader = CSVReader(fr)
            reader.use { r ->
                var line = r.readNext()

                while (line != null) {
                    var key: Int  = 0;
                    DataSource.hospitals.add(
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

        var location: Int = 0

        spinner.onItemSelectedListener = object :  AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                location = position
                binding.hospitalListContainer.adapter = HospitalListAdapter(
                    applicationContext, filterName, location
                )
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.hospitalListContainer.adapter = HospitalListAdapter(
            applicationContext, filterName, location
        )

        ArrayAdapter.createFromResource(
            this,
            R.array.location_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }
}