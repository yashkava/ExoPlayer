package com.example.exoplayer

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi

class HomeActivity : AppCompatActivity() {
    lateinit var exoplayer:Button
    lateinit var youtube:Button
    lateinit var exoyoutube:Button
    @RequiresApi(Build.VERSION_CODES.R)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        exoplayer = findViewById(R.id.btnExoplayer)
        youtube = findViewById(R.id.btnYoutube)
        exoyoutube = findViewById(R.id.btnExoYoutube)

        youtube.setOnClickListener {
            val i = Intent(this,YoutubeActivity::class.java)
            startActivity(i)
        }
        exoplayer.setOnClickListener {
            val i = Intent(this,MainActivity::class.java)
            startActivity(i)
        }
        exoyoutube.setOnClickListener {
            val i = Intent(this,YoutubeExoActivity::class.java)
            startActivity(i)
        }
    }
}