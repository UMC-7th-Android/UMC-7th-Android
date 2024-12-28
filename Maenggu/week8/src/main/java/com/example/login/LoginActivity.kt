package com.example.login

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var databaseHelper: UserDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // 데이터베이스 초기화
        databaseHelper = UserDatabaseHelper(this)

        // 이메일 및 비밀번호 입력 필드
        val etEmail = findViewById<EditText>(R.id.et_email)
        val etPassword = findViewById<EditText>(R.id.et_password)

        // 로그인 버튼
        val btnLogin = findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "이메일과 비밀번호를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else {
                val userId = databaseHelper.validateUser(email, password)
                if (userId != -1) {
                    saveUserToSharedPreferences(userId)
                    Toast.makeText(this, "로그인 성공! ID: $userId", Toast.LENGTH_SHORT).show()
                    // 추가 작업: 다음 화면으로 이동 가능
                } else {
                    Toast.makeText(this, "로그인 실패: 이메일 또는 비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // 아이디/비밀번호 찾기
        val tvFindInfo = findViewById<TextView>(R.id.tv_find_info)
        tvFindInfo.setOnClickListener {
            Toast.makeText(this, "아이디/비밀번호 찾기 기능은 미구현입니다.", Toast.LENGTH_SHORT).show()
        }
    }

    // SharedPreferences에 사용자 ID 저장
    private fun saveUserToSharedPreferences(userId: Int) {
        val sharedPref = getSharedPreferences("LoginPrefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putInt("user_id", userId) // 사용자 ID 저장
            apply()
        }
    }
}
