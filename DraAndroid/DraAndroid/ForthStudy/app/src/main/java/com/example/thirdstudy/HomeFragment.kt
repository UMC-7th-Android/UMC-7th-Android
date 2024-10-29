package com.example.thirdstudy

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fragment_home 레이아웃을 이 프래그먼트에 연결
        return inflater.inflate(R.layout.fragment_home, container, false)

    }
}





//package com.example.thirdstudy


//
//import BannerAdapter
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.viewpager2.widget.ViewPager2
//import com.google.android.material.tabs.TabLayout
//import com.google.android.material.tabs.TabLayoutMediator
//
//class HomeFragment : Fragment() {
//
//    private lateinit var viewPager: ViewPager2
//    private lateinit var tabLayout: TabLayout
//    private lateinit var bannerAdapter: BannerAdapter
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // fragment_home 레이아웃을 이 프래그먼트에 연결
//        val view = inflater.inflate(R.layout.fragment_home, container, false)
//
//        // ViewPager2와 TabLayout 초기화
//        viewPager = view.findViewById(R.id.home_banner_viewpager)
//        tabLayout = view.findViewById(R.id.home_banner_tab_layout)
//
//        // BannerAdapter 설정
//        bannerAdapter = BannerAdapter(getBannerImages())
//        viewPager.adapter = bannerAdapter
//
//        // TabLayout과 ViewPager2 연결
//        TabLayoutMediator(tabLayout, viewPager) { _, _ -> }.attach()
//
//        return view
//    }
//
//    // 배너 이미지들을 가져오는 함수
//    private fun getBannerImages(): List<Int> {
//        return listOf(
//            R.drawable.img_home_viewpager_exp,
//            R.drawable.home_pannel_album_img_02_iv,
//            R.drawable.img_home_viewpager_exp2
//        )
//    }
//}
