package com.example.hsu_umc_android_7th

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val button = findViewById<ImageButton>(R.id.imageButton)
        button.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent) }
        val button2 = findViewById<ImageButton>(R.id.imageButton2)
        button.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)}
        val button3 = findViewById<ImageButton>(R.id.imageButton3)
        button.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)}
        val button5 = findViewById<ImageButton>(R.id.imageButton5)
        button.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)}
        val button4 = findViewById<ImageButton>(R.id.imageButton4)
        button.setOnClickListener {
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)}
    }
}