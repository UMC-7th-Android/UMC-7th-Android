package com.example.songapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SongActivity : AppCompatActivity() {

    private lateinit var dbHelper: SongsDatabaseHelper
    private lateinit var sharedPreferences: SharedPreferences
    private var nowPos: Int = -1
    private var songList: MutableList<Song> = mutableListOf()  // 곡 리스트

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        dbHelper = SongsDatabaseHelper(this)
        sharedPreferences = getSharedPreferences("SongPreferences", Context.MODE_PRIVATE)

        // Song 데이터 초기화 및 DB에서 가져오기
        loadSongs()

        // 하트 버튼 클릭
        val heartButton = findViewById<ImageView>(R.id.heart_button)
        heartButton.setOnClickListener {
            toggleLikeStatus()  // 하트 클릭 시 좋아요 상태 토글
        }

        // 초기 곡 정보 설정
        if (nowPos != -1 && nowPos < songList.size) {
            updateCurrentSong()
        }

        // 뒤로 가기 버튼
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            val intent = Intent()
            intent.putExtra("goToHome", true) // 홈으로 이동 신호 전달
            setResult(RESULT_OK, intent)
            finish()
        }

        // 이전 곡 버튼
        val previousBtn = findViewById<ImageView>(R.id.previous_btn)
        previousBtn.setOnClickListener {
            if (nowPos > 0) {
                nowPos--  // 이전 곡
                updateCurrentSong()
            } else {
                Toast.makeText(this, "이전 곡이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }

        // 다음 곡 버튼
        val nextBtn = findViewById<ImageView>(R.id.next_btn)
        nextBtn.setOnClickListener {
            if (nowPos < songList.size - 1) {
                nowPos++  // 다음 곡
                updateCurrentSong()
            } else {
                Toast.makeText(this, "다음 곡이 없습니다.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // DB에서 곡 리스트 로드
    private fun loadSongs() {
        val cursor = dbHelper.getAllSongs()
        songList.clear()  // 리스트 초기화

        while (cursor.moveToNext()) {
            val songId = cursor.getInt(cursor.getColumnIndexOrThrow("songId"))
            val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
            val artist = cursor.getString(cursor.getColumnIndexOrThrow("artist"))
            val imageResId = cursor.getInt(cursor.getColumnIndexOrThrow("imageResId"))
            val isLike = cursor.getInt(cursor.getColumnIndexOrThrow("isLike")) == 1

            songList.add(Song(songId, title, artist, imageResId, isLike))  // Song 객체 추가
        }
        cursor.close()

        if (songList.isNotEmpty()) {
            nowPos = 0  // 첫 번째 곡으로 초기화
        }
    }

    // 좋아요 상태 토글
    private fun toggleLikeStatus() {
        if (nowPos != -1 && nowPos < songList.size) {
            val currentSong = songList[nowPos]
            currentSong.isLike = !currentSong.isLike

            // DB에서 좋아요 상태 업데이트
            updateSongLikeStatus(currentSong)

            // 하트 버튼 상태 반영
            val heartButton = findViewById<ImageView>(R.id.heart_button)
            if (currentSong.isLike) {
                heartButton.setImageResource(R.drawable.ic_my_like_on)  // 채워진 하트
            } else {
                heartButton.setImageResource(R.drawable.ic_my_like_off)  // 빈 하트
            }
        }
    }

    // DB에서 곡의 isLike 값 업데이트
    private fun updateSongLikeStatus(song: Song) {
        dbHelper.updateSongLikeStatus(song.songId, song.isLike)
    }

    // 현재 곡 정보 업데이트
    private fun updateCurrentSong() {
        if (nowPos != -1 && nowPos < songList.size) {
            val song = songList[nowPos]
            // SharedPreferences에 songId 저장
            sharedPreferences.edit().putInt("songId", song.songId).apply()

            // 화면에 곡 정보 표시
            findViewById<TextView>(R.id.textView).text = song.title
            findViewById<TextView>(R.id.artist_name).text = song.artist
            findViewById<ImageView>(R.id.bruno).setImageResource(song.imageResId)

            // 하트 버튼 상태 반영
            val heartButton = findViewById<ImageView>(R.id.heart_button)
            if (song.isLike) {
                heartButton.setImageResource(R.drawable.ic_my_like_on)
            } else {
                heartButton.setImageResource(R.drawable.ic_my_like_off)
            }
        }
    }
}






