package com.example.khidicontest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.khidicontest.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val searchBtn: Button = findViewById(R.id.b_researchhospital)
        val helpBtn: Button = findViewById(R.id.b_help)

        searchBtn.setOnClickListener {
            val intent = Intent(this, SearchHospitalActivity::class.java)
            startActivity(intent)
        }

        helpBtn.setOnClickListener {
            val intent = Intent(this, HelpActivity::class.java)
            startActivity(intent)
        }
    }
}