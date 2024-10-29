package com.example.loopy

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
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

        val b1 = findViewById<ImageView>(R.id.a)
        b1.setOnClickListener {
            val intent = Intent(this@MainActivity,MainActivity2::class.java).apply {
                putExtra("image", R.id.a)
                val title = findViewById<TextView>(R.id.a_d)
                putExtra("title", title.text.toString())
                startActivity(this)
            }
        }

        val b2 = findViewById<ImageView>(R.id.b)
        b2.setOnClickListener {
            val intent = Intent(this@MainActivity,MainActivity2::class.java).apply {
                putExtra("image", R.id.b)
                val title = findViewById<TextView>(R.id.b_d)
                putExtra("title", title.text.toString())
                startActivity(this)
            }
        }

        val b3 = findViewById<ImageView>(R.id.c)
        b3.setOnClickListener {
            val intent = Intent(this@MainActivity,MainActivity2::class.java).apply {
                putExtra("image", R.id.c)
                val title = findViewById<TextView>(R.id.c_d)
                putExtra("title", title.text.toString())
                startActivity(this)
            }
        }

        val b4 = findViewById<ImageView>(R.id.d)
        b4.setOnClickListener {
            val intent = Intent(this@MainActivity,MainActivity2::class.java).apply {
                putExtra("image", R.id.d)
                val title = findViewById<TextView>(R.id.d_d)
                putExtra("title", title.text.toString())
                startActivity(this)
            }
        }

        val b5 = findViewById<ImageView>(R.id.e)
        b5.setOnClickListener {
            val intent = Intent(this@MainActivity,MainActivity2::class.java).apply {
                putExtra("image", R.id.e)
                val title = findViewById<TextView>(R.id.e_d)
                putExtra("title", title.text.toString())
                startActivity(this)
            }
        }
    }
}