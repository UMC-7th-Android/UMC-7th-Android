package com.example.secstudy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.secstudy.ExcitedFragment
import com.example.secstudy.AnxietyFragment
import com.example.secstudy.AngerFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation_view)

        // 처음 시작할 때 기본 Fragment를 설정
        if (savedInstanceState == null) {
            loadFragment(ExcitedFragment(), false)
        }

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(ExcitedFragment(), true) // 슬라이드 애니메이션 적용
                    true
                }
                R.id.navigation_dashboard -> {
                    loadFragment(AnxietyFragment(), true) // 슬라이드 애니메이션 적용
                    true
                }
                R.id.navigation_notifications -> {
                    loadFragment(AngerFragment(), true) // 슬라이드 애니메이션 적용
                    true
                }
                else -> false
            }
        }
    }

    // Fragment 로드 메서드, 애니메이션을 추가
    private fun loadFragment(fragment: Fragment, animate: Boolean) {
        val transaction = supportFragmentManager.beginTransaction()

        // 애니메이션 설정
        if (animate) {
            transaction.setCustomAnimations(
                android.R.anim.fade_in, // fade in xml
                android.R.anim.fade_out // fade out xml
            )
        }

        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}
