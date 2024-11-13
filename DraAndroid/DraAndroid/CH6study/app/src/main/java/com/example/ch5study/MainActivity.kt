package com.example.ch5study

import HomeFragment
import LockerFragment
import LookFragment
import SearchFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch5study.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    // ViewBinding을 위한 변수 선언
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ViewBinding 초기화
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 첫 화면에 HomeFragment를 기본으로 설정
        supportFragmentManager.beginTransaction()
            .replace(binding.mainFrm.id, HomeFragment())
            .commit()

        // BottomNavigationView의 아이템 선택 리스너
        binding.mainBnv.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.mainFrm.id, HomeFragment())
                        .commit()
                    supportActionBar?.title = "Home"
                    true
                }
                R.id.aroundFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.mainFrm.id, LookFragment())
                        .commit()
                    supportActionBar?.title = "Search"
                    true
                }
                R.id.searchFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.mainFrm.id, SearchFragment())
                        .commit()
                    supportActionBar?.title = "Library"
                    true
                }
                R.id.lockerFragment -> {
                    supportFragmentManager.beginTransaction()
                        .replace(binding.mainFrm.id, LockerFragment())
                        .commit()
                    supportActionBar?.title = "Home"
                    true
                }
                else -> false
            }
        }
    }
}
