package com.example.mission3

// FragmentHome.kt

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2

class HomeFragment : Fragment() {

    private lateinit var homeBannerVp: ViewPager2
    private lateinit var newJeansIv: ImageView
    private lateinit var bannerAdapter: HomeBannerAdapter

    // 배너 레이아웃 리소스 목록
    private val bannerLayouts = listOf(
        R.layout.fragment_banner_1,
        R.layout.fragment_banner_2,
        R.layout.fragment_banner_3
        // 추가 배너 레이아웃 리소스
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewPager2 초기화 및 어댑터 설정
        homeBannerVp = view.findViewById(R.id.home_banner_vp)
        bannerAdapter = HomeBannerAdapter(bannerLayouts) { position ->
            // 배너 이미지 클릭 시의 동작 (필요 시 구현)
            // 현재 예시에서는 "newjeans" 이미지 클릭 시 FragmentAlbum으로 전환하도록 설정
        }
        homeBannerVp.adapter = bannerAdapter

        // NewJeans ImageView 초기화 및 클릭 리스너 설정
        newJeansIv = view.findViewById(R.id.newjeans_iv)
        newJeansIv.setOnClickListener {
            navigateToAlbumFragment()
        }
    }

    private fun navigateToAlbumFragment() {
        // FragmentAlbum 인스턴스 생성
        val fragmentAlbum = AlbumFragment()

        // Fragment 전환
        parentFragmentManager.beginTransaction()
            .replace(R.id.main, fragmentAlbum) // 'main'은 activity_main.xml의 FrameLayout ID
            .addToBackStack(null) // 백 스택에 추가하여 뒤로 가기 가능하게 함
            .commit()
    }
}
