package com.example.mission3

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.example.mission3.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeBannerVp: ViewPager2
    private lateinit var newJeansIv: ImageView
    private lateinit var bannerAdapter: HomeBannerAdapter
    private lateinit var binding: FragmentHomeBinding

    // 배너 레이아웃 리소스 목록
    private val bannerLayouts = listOf(
        R.layout.fragment_banner_1,
        R.layout.fragment_banner_2,
        R.layout.fragment_banner_3
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // mainPlayerCl 클릭 시 SongActivity로 이동
        binding.mainPlayerCl.setOnClickListener {
            // Song 객체 생성
            val song = Song(
                title = binding.miniPlayerSongTitleTv.text.toString(),
                singer = binding.miniPlayerSingerTv.text.toString(),
                second = 0,
                playTime = 191,
                isPlaying = false
            )

            // SongActivity 시작
            val intent = Intent(requireContext(), SongActivity::class.java) // MainActivity 대신 SongActivity로 수정
            // putExtra로 데이터 전송
            intent.putExtra("title", song.title)
            intent.putExtra("singer", song.singer)
            intent.putExtra("second", song.second)
            intent.putExtra("playTime", song.playTime)
            intent.putExtra("isPlaying", song.isPlaying)
            startActivity(intent)
        }

        // ViewPager2 초기화 및 어댑터 설정
        homeBannerVp = binding.homeBannerVp
        bannerAdapter = HomeBannerAdapter(bannerLayouts) { position -> }
        homeBannerVp.adapter = bannerAdapter

        // NewJeans ImageView 초기화 및 클릭 리스너 설정
        newJeansIv = binding.newjeansIv
        newJeansIv.setOnClickListener {
            navigateToAlbumFragment()
        }
    }

    private fun navigateToAlbumFragment() {
        // FragmentAlbum 인스턴스 생성
        val fragmentAlbum = AlbumFragment()

        // Fragment 전환
        parentFragmentManager.beginTransaction()
            .replace(R.id.main, fragmentAlbum)
            .addToBackStack(null)
            .commit()
    }
}
