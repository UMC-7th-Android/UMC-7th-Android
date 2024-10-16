package com.example.thirdstudy


import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bt_nav)

        // 첫 화면에 HomeFragment를 기본으로 설정
        supportFragmentManager.beginTransaction()
            .replace(R.id.main, HomeFragment())
            .commit()

        // BottomNavigationView의 아이템 선택 리스너
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main, HomeFragment())
                        .commit()
                    supportActionBar?.title = "Home"
                    true
                }
                R.id.menu_search -> {
                    // Search 버튼을 눌렀을 때 동작
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main, SearchFragment())
                        .commit()
                    supportActionBar?.title = "Search"
                    true
                }
                R.id.menu_lib -> {
                    // Library 버튼을 눌렀을 때 동작
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main, LibFragment())
                        .commit()
                    supportActionBar?.title = "Library"
                    true
                }
                R.id.menu_me -> {
                    // Library 버튼을 눌렀을 때 동작
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main, LockerFragment())
                        .commit()
                    supportActionBar?.title = "Home"
                    true
                }
                else -> false
            }
        }
    }
}