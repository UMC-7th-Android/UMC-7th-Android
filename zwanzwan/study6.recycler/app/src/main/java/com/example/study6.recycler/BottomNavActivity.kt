//package com.example.study2real
//
//import android.os.Bundle
//import androidx.appcompat.app.AppCompatActivity
//import com.google.android.material.bottomnavigation.BottomNavigationView
//
//class BottomNavActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
//        bottomNav.setOnNavigationItemSelectedListener {
//            when (it.itemId) {
//                R.id.menu_music -> {
//                    supportFragmentManager.beginTransaction()
//                        .replace(R.id.fragment_container, MusicListFragment())
//                        .commit()
//                    true
//                }
//                else -> false
//            }
//        }
//
//         기본 프래그먼트를 MusicListFragment로 설정
//        if (savedInstanceState== null) {
//            bottomNav.selectedItemId = R.id.menu_music
//        }
//    }
//}
