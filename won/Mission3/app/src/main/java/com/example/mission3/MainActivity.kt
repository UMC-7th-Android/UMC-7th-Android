package com.example.mission3

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import com.example.mission3.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var bottomNavi: BottomNavigationView
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // HomeFragment가 기본 프래그먼트로 표시되도록 설정
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main, HomeFragment())
                .commit()
        }

        // 상태 표시줄 색상을 흰색으로 설정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#FFFFFF")
        }

        bottomNavi = binding.bottomNavi // 바인딩 객체를 통해 BottomNavigationView 참조

        // Song 객체 생성
        val song = Song(
            title = "Supernatural",
            singer = "NewJeans",
            second = 0,
            playTime = 191,
            isPlaying = false
        )

        // mainPlayerCl을 바인딩을 통해 참조
        val mainPlayerCl = binding.mainPlayerCl

        // mainPlayerCl 눌렀을 때 SongActivity로 이동
        mainPlayerCl.setOnClickListener {
            val intent = Intent(this, SongActivity::class.java)

            // putExtra를 사용해서 데이터값들을 보내줌
            intent.putExtra("title", song.title)
            intent.putExtra("singer", song.singer)
            intent.putExtra("second", song.second)
            intent.putExtra("playTime", song.playTime)
            intent.putExtra("isPlaying", song.isPlaying)

            startActivity(intent)
        }

        // BottomNavigationView 아이템 선택 리스너 설정
        bottomNavi.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.action_home -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main, HomeFragment())
                        .commit()
                    true
                }
                R.id.action_audio -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main, AudioFrag())
                        .commit()
                    true
                }
                R.id.action_search -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main, SearchFrag())
                        .commit()
                    true
                }
                R.id.action_mymusic -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main, MyMusicFrag())
                        .commit()
                    true
                }
                R.id.action_menu -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.main, MenuFrag())
                        .commit()
                    true
                }
                else -> false
            }
        }
    }
}

