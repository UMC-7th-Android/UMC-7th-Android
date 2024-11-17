package com.example.mission6

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // 스플래시 화면 레이아웃 설정

        // 일정 시간 후 MainActivity로 이동 (2초 후)
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish() // 스플래시 액티비티 종료
        }, 2000) // 2초 딜레이
    }
}
