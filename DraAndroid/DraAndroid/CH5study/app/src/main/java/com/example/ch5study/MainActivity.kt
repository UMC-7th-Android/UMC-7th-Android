package com.example.ch5study

import HomeFragment
import LockerFragment
import LookFragment
import SearchFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bt_nav)

        // 첫 화면에 HomeFragment를 기본으로 설정
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_frm, HomeFragment())
            .commit()

        // BottomNavigationView의 아이템 선택 리스너
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, HomeFragment())
                        .commit()
                    supportActionBar?.title = "Home"
                    true
                }
                R.id.aroundFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LookFragment())
                        .commit()
                    supportActionBar?.title = "Search"
                    true
                }
                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commit()
                    supportActionBar?.title = "Library"
                    true
                }
                R.id.lockerFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LockerFragment())
                        .commit()
                    supportActionBar?.title = "Home"
                    true
                }
                else -> false
            }
        }
    }
}
