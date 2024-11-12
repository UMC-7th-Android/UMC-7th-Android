package com.example.study5

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ConfirmActivity : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm)

        val textViewConfirmMemo: TextView = findViewById(R.id.textViewConfirmMemo)
        val backImage: ImageView = findViewById(R.id.back) // ImageView 참조

        // 메모 내용 받기
        val memo = intent.getStringExtra("memo")
        textViewConfirmMemo.text = memo ?: "메모 내용이 없습니다."

        // Back Image 클릭 리스너 설정
        backImage.setOnClickListener {
            finish() // 현재 액티비티 종료, MainActivity로 돌아감
        }
    }
}


