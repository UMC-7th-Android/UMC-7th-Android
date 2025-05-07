package com.example.login



import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

import androidx.viewpager2.widget.ViewPager2


class HomeFragment : Fragment() {

    private lateinit var viewPager: ViewPager2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // ViewPager2 초기화
        viewPager = view.findViewById(R.id.banner_viewpager)

        // 배너 데이터
        val bannerImages = listOf(
            R.drawable.banner1, // drawable 리소스 ID
            R.drawable.banner3,
            R.drawable.banner2
        )

        // Adapter 연결


        // 기존 텍스트 설정 유지
        view.findViewById<TextView>(R.id.text_view).text = "홈 화면"

        // 회원가입 버튼 클릭 이벤트 추가
        val btnSignUp = view.findViewById<Button>(R.id.btn_signup)
        btnSignUp.setOnClickListener {
            // SignUpFragment로 이동
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, SignUpFragment())
                .addToBackStack(null) // 뒤로가기 버튼 처리
                .commit()
        }

        return view
    }
}
