package com.example.hsu_umc_android_7th

import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Excited face 클릭 이벤트
        val excitedFace: ImageView = findViewById(R.id.excitedFace)
        excitedFace.setOnClickListener {
            loadFragment(ExcitedFragment())
        }

        // Normal face 클릭 이벤트
        val normalFace: ImageView = findViewById(R.id.normalFace)
        normalFace.setOnClickListener {
            loadFragment(NormalFragment())
        }

        // Happy face 클릭 이벤트
        val happyFace: ImageView = findViewById(R.id.happyFace)
        happyFace.setOnClickListener {
            loadFragment(HappyFragment())
        }

        // Anger face 클릭 이벤트
        val angerFace: ImageView = findViewById(R.id.angerFace)
        angerFace.setOnClickListener {
            loadFragment(AngerFragment())
        }

        // Anxiety face 클릭 이벤트
        val anxietyFace: ImageView = findViewById(R.id.anxietyFace)
        anxietyFace.setOnClickListener {
            loadFragment(AnxietyFragment())
        }
    }

    // Fragment 전환을 위한 함수
    private fun loadFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}