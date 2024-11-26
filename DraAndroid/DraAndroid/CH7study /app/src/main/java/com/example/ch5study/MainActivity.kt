package com.example.ch5study

import HomeFragment
import LockerFragment
import LookFragment
import SearchFragment
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // FirebaseAuth 초기화
        auth = FirebaseAuth.getInstance()

        // 현재 로그인 상태 확인
        if (auth.currentUser == null) {
            // 로그인되지 않은 상태라면 LoginActivity로 이동
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }

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
                    true
                }
                R.id.aroundFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LookFragment())
                        .commit()
                    true
                }
                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, SearchFragment())
                        .commit()
                    true
                }
                R.id.lockerFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main_frm, LockerFragment())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}
