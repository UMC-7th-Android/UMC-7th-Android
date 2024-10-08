package com.example.secstudy

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 레이아웃을 설정하여 스플래시 화면을 표시 (res/layout/activity_splash.xml 필요)
        setContentView(R.layout.activity_splash)

        // 일정 시간이 지나면 MainActivity로 전환 (여기서는 3초)
        Handler(Looper.getMainLooper()).postDelayed({
            // MainActivity로 전환
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // 스플래시 화면 종료
            finish()
        }, 2000) // 3000 밀리초 = 3초
    }
}
