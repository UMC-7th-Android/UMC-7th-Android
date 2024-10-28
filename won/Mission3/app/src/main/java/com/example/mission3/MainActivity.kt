package com.example.mission3

import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mission3.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavi: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 상태 표시줄 색상을 흰색으로 설정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, android.R.color.white)
        }

        bottomNavi = findViewById(R.id.bottom_navi)

        // 초기 프래그먼트 설정
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main, HomeFragment())
                .commit()
        }

        // BottomNavigationView 아이템 선택 리스너 설정
        bottomNavi.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main, HomeFragment())
                        .commit()
                    true
                }
                R.id.action_audio -> {
                    // FragmentAudio 구현 필요
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main, AudioFrag())
                        .commit()
                    true
                }
                R.id.action_search -> {
                    // FragmentSearch 구현 필요
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main, SearchFrag())
                        .commit()
                    true
                }
                R.id.action_mymusic -> {
                    // FragmentMyMusic 구현 필요
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main, MyMusicFrag())
                        .commit()
                    true
                }
                R.id.action_menu -> {
                    // FragmentMenu 구현 필요
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main, MenuFrag())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }

}