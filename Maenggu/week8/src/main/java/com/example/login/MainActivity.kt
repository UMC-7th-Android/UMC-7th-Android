package com.example.login

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.SeekBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_SONG = 1001 // 요청 코드를 상수로 정의
    }

    private lateinit var seekBar: SeekBar
    private var isUpdatingSeekBar = false
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 바텀 네비게이션 초기화
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // 처음 실행 시 기본 프래그먼트 설정
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        // 바텀 네비게이션 클릭 리스너 설정
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val selectedFragment: Fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                else -> HomeFragment()
            }

            // 프래그먼트 변경
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit()

            true // 반드시 true를 반환해야 클릭 이벤트가 처리됩니다.
        }
    }

    // onActivityResult는 클래스 레벨에서 선언
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SONG && resultCode == RESULT_OK) {
            val goToHome = data?.getBooleanExtra("goToHome", false) ?: false
            if (goToHome) {
                // 홈 프래그먼트로 전환
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment())
                    .commit()

                // 바텀 네비게이션의 선택된 항목 업데이트
                findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId =
                    R.id.nav_home
            }
        }
    }
}
