package com.example.ch5study

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        auth = FirebaseAuth.getInstance()

        val emailEditText = findViewById<EditText>(R.id.login_id_et)
        val passwordEditText = findViewById<EditText>(R.id.login_password_et)
        val loginButton = findViewById<Button>(R.id.login_sign_in_btn)
        val signUpTextView = findViewById<TextView>(R.id.login_sign_up_tv) // 회원가입 텍스트뷰

        // 회원가입 버튼 클릭 시 회원가입 화면으로 이동
        signUpTextView.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        // 로그인 버튼 클릭 이벤트
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "이메일과 비밀번호를 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "로그인 성공!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java)) // 메인 화면으로 이동
                        finish()
                    } else {
                        Toast.makeText(this, "로그인 실패: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        Log.e("Login", "Error: ${task.exception?.message}")
                    }
                }
        }
    }
}
