package com.example.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment

class SignUpFragment : Fragment() {

    private lateinit var databaseHelper: UserDatabaseHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_sign_up, container, false)

        // 데이터베이스 초기화
        databaseHelper = UserDatabaseHelper(requireContext())

        // 이메일 및 비밀번호 입력 필드
        val etEmail = view.findViewById<EditText>(R.id.et_email)
        val etPassword = view.findViewById<EditText>(R.id.et_password)
        val etPasswordConfirm = view.findViewById<EditText>(R.id.et_password_confirm)

        // 가입 완료 버튼
        val btnSignUp = view.findViewById<Button>(R.id.btn_signup)
        btnSignUp.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val passwordConfirm = etPasswordConfirm.text.toString().trim()

            // 입력값 검증
            if (email.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
                Toast.makeText(requireContext(), "모든 필드를 입력해주세요.", Toast.LENGTH_SHORT).show()
            } else if (password != passwordConfirm) {
                Toast.makeText(requireContext(), "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show()
            } else {
                // 데이터베이스에 사용자 추가
                val isAdded = databaseHelper.addUser(email, password)
                if (isAdded) {
                    Toast.makeText(requireContext(), "회원가입 성공!", Toast.LENGTH_SHORT).show()
                    // 로그인 화면으로 이동
                    val intent = Intent(requireContext(), LoginActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(requireContext(), "이미 등록된 이메일입니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        // 로그인 버튼
        val btnLogin = view.findViewById<Button>(R.id.btn_login)
        btnLogin.setOnClickListener {
            // 로그인 화면으로 이동
            val intent = Intent(requireContext(), LoginActivity::class.java)
            startActivity(intent)
        }

        return view
    }
}
