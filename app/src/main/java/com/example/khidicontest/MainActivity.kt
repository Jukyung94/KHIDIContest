package com.example.khidicontest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.khidicontest.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var listIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.enterButton.setOnClickListener {
            listIntent = Intent(this, SearchHospitalActivity::class.java)
            startActivity(listIntent)
        }
    }
}