package com.example.songapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_SONG = 1001 // 요청 코드를 상수로 정의
    }

    private lateinit var seekBar: SeekBar
    private var isUpdatingSeekBar = false

    private lateinit var dbHelper: SongsDatabaseHelper
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // DB 및 SharedPreferences 초기화
        dbHelper = SongsDatabaseHelper(this)
        sharedPreferences = getSharedPreferences("SongPreferences", Context.MODE_PRIVATE)

        // 더미 데이터 삽입
        insertDummyData()

        // 저장된 songId 가져오기 및 초기화
        val savedSongId = sharedPreferences.getInt("songId", -1)
        if (savedSongId != -1) {
            initializeSong(savedSongId)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        // 처음 실행 시 기본 프래그먼트 설정
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        val btnOpenSongActivity = findViewById<ImageView>(R.id.btnOpenSongActivity)
        btnOpenSongActivity.setOnClickListener {
            val intent = Intent(this, SongActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SONG)
        }


        // 버튼 클릭 시 SongActivity로 이동
        btnOpenSongActivity.setOnClickListener {
            val intent = Intent(this, SongActivity::class.java)
            startActivityForResult(intent, REQUEST_CODE_SONG) // startActivityForResult로 변경
        }

        // 바텀 네비게이션 클릭 리스너 설정
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            val selectedFragment: Fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_search -> SearchFragment()
                R.id.nav_explore -> ExploreFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> HomeFragment()
            }

            // 프래그먼트 변경
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit()

            true // 반드시 true를 반환해야 클릭 이벤트가 처리됩니다.
        }

        // SeekBar 설정
        seekBar = findViewById(R.id.seekBar)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    // SeekBar를 조정할 때의 처리
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
                isUpdatingSeekBar = true
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                isUpdatingSeekBar = false
            }
        })
    }
    private fun logAllSongs() {
        val cursor = dbHelper.getAllSongs()
        while (cursor.moveToNext()) {
            val songId = cursor.getInt(cursor.getColumnIndexOrThrow("songId"))
            val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
            val artist = cursor.getString(cursor.getColumnIndexOrThrow("artist"))
            val imageResId = cursor.getInt(cursor.getColumnIndexOrThrow("imageResId"))
            Log.d("DB_LOG", "Song ID: $songId, Title: $title, Artist: $artist, ImageResId: $imageResId")
        }
        cursor.close()
    }

    // 더미 데이터 삽입
    private fun insertDummyData() {
        // 기존 데이터 초기화
        dbHelper.clearAllSongs()

        // 새로운 더미 데이터 삽입
        val dummySongs = listOf(
            Triple("The Lazy Song", "Bruno Mars", R.drawable.the_lazy),
            Triple("우리사랑하지말아요", "BingBang", R.drawable.bigbang),
            Triple("산책", "백예린", R.drawable.yerin),
            Triple("꺼내먹어요", "ZionT", R.drawable.ziont),
            Triple("커피가게아가씨", "Bignathy", R.drawable.bignath)
        )

        // 더미 데이터를 DB에 삽입
        dummySongs.forEach { (title, artist, imageResId) ->
            dbHelper.insertSong(title, artist, imageResId)
        }
    }


    // 초기화 시 저장된 songId로 곡 로드
    private fun initializeSong(songId: Int) {
        val cursor = dbHelper.getSongById(songId)
        if (cursor.moveToFirst()) {
            val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
            val artist = cursor.getString(cursor.getColumnIndexOrThrow("artist"))
            Log.d("MainActivity", "Current Song: $title by $artist")
        }
        cursor.close()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SONG && resultCode == RESULT_OK) {
            val goToHome = data?.getBooleanExtra("goToHome", false) ?: false
            if (goToHome) {
                // 홈 프래그먼트로 전환
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, HomeFragment())
                    .commit()

                // 바텀 네비게이션의 선택된 항목 업데이트
                findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.nav_home
            }
        }
    }
}



