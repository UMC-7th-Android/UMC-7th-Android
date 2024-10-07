package com.example.bottomnavigation

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavi: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavi = findViewById(R.id.bottom_navi)

        // 상태 바 색상 설정 (테마에서 이미 설정했다면 필요 없음)
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)

        // 상태 바 아이콘 색상 설정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (isColorDark(ContextCompat.getColor(this, R.color.white))) {
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                window.decorView.systemUiVisibility = 0
            }
        }


        // 초기 프래그먼트 설정 (앱 실행 시 Frag1 표시)
        if (savedInstanceState == null) {
            changeFragment(Frag1())
            bottomNavi.selectedItemId = R.id.action_home
        }

        // BottomNavigationView 아이템 선택 리스너 설정
        bottomNavi.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    changeFragment(Frag1())
                    true
                }
                R.id.action_library_books -> {
                    changeFragment(Frag2())
                    true
                }
                R.id.action_location_on -> {
                    changeFragment(Frag3())
                    true
                }
                R.id.action_chat -> {
                    changeFragment(Frag4())
                    true
                }
                R.id.action_person -> {
                    changeFragment(Frag5())
                    true
                }
                else -> false
            }
        }
    }

    /**
     * 프래그먼트를 전환하는 함수
     * @param fragment 전환할 프래그먼트
     */
    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            // 커스텀 애니메이션 설정
            setCustomAnimations(
                R.anim.frg_transition_fade_in,  // enter 애니메이션
                R.anim.frg_transition_fade_out, // exit 애니메이션
                R.anim.frg_transition_fade_in,  // popEnter 애니메이션 (백 스택에서 돌아올 때)
                R.anim.frg_transition_fade_out  // popExit 애니메이션 (백 스택에서 나갈 때)
            )
            replace(R.id.main_frame, fragment) // 프래그먼트 교체
            addToBackStack(fragment::class.java.simpleName) // 백 스택에 추가
            commit() // 트랜잭션 커밋
        }
    }
    private fun isColorDark(color: Int): Boolean {
        val darkness: Double =
            1 - (0.299 * android.graphics.Color.red(color) +
                    0.587 * android.graphics.Color.green(color) +
                    0.114 * android.graphics.Color.blue(color)) / 255
        return darkness >= 0.5
    }
}