package com.example.ch5study

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_singup)

        auth = FirebaseAuth.getInstance()

        val emailEditText = findViewById<EditText>(R.id.sign_up_id_et)
        val passwordEditText = findViewById<EditText>(R.id.sign_up_password_et)
        val confirmPasswordEditText = findViewById<EditText>(R.id.sign_up_password_check_et)
        val signUpButton = findViewById<Button>(R.id.sign_up_sign_up_btn)

        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()
            val confirmPassword = confirmPasswordEditText.text.toString().trim()

            // 모든 필드 입력 여부 확인
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "모든 필드를 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 이메일 유효성 검사
            if (!isValidEmail(email)) {
                Toast.makeText(this, "올바른 이메일 형식을 입력하세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 비밀번호 최소 길이 확인
            if (password.length < 6) {
                Toast.makeText(this, "비밀번호는 최소 6자 이상이어야 합니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // 비밀번호 일치 여부 확인
            if (password != confirmPassword) {
                Toast.makeText(this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Firebase 회원가입
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show()
                        finish() // 회원가입 성공 후 화면 종료
                    } else {
                        // Firebase 에러 처리
                        val errorCode = task.exception?.message
                        Log.e("SignUp", "Error: $errorCode")
                        if (errorCode != null) {
                            handleFirebaseError(errorCode)
                        } else {
                            Toast.makeText(this, "회원가입 실패", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
        }
    }

    // 이메일 유효성 검사 함수
    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }

    // Firebase 에러 메시지 처리
    private fun handleFirebaseError(errorMessage: String) {
        when {
            errorMessage.contains("The email address is badly formatted") -> {
                Toast.makeText(this, "잘못된 이메일 형식입니다.", Toast.LENGTH_SHORT).show()
            }
            errorMessage.contains("The email address is already in use") -> {
                Toast.makeText(this, "이미 사용 중인 이메일입니다.", Toast.LENGTH_SHORT).show()
            }
            else -> {
                Toast.makeText(this, "회원가입 실패: $errorMessage", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
