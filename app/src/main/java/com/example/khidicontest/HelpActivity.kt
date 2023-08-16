package com.example.khidicontest

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class HelpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_help2)

        val medicalDispute: Button = findViewById(R.id.b_KMDMAA)
        val medicalKorea: Button = findViewById(R.id.b_MKIC)

        medicalDispute.setOnClickListener {
            val queryUrl: Uri = Uri.parse("https://www.k-medi.or.kr/")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            startActivity(intent)
        }

        medicalKorea.setOnClickListener {
            val queryUrl: Uri = Uri.parse("https://www.medicalkorea.or.kr/")
            val intent = Intent(Intent.ACTION_VIEW, queryUrl)
            startActivity(intent)
        }
    }
}