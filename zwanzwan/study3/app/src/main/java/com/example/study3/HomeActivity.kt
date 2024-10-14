package com.example.study3

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // "back" 이미지 뷰 클릭 리스너 설정
        val backImageView: ImageView = findViewById(R.id.back) // "back" 이미지 뷰의 ID에 맞게 수정
        backImageView.setOnClickListener {
            // MainActivity로 돌아가기
            finish() // 현재 Activity 종료
        }
    }
}
