package com.example.thirdstudy

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SongActivity : AppCompatActivity() {

    private lateinit var songTitleTextView: TextView
    private lateinit var songArtistTextView: TextView
    private lateinit var repeatButton: ImageView
    private lateinit var randomButton: ImageView
    private lateinit var playButton: ImageView
    private lateinit var pauseButton: ImageView

    private var isRepeating = false
    private var isRandom = false
    private var isPlaying = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_song)

        // 뷰 초기화
        songTitleTextView = findViewById(R.id.song_music_title_tv)
        songArtistTextView = findViewById(R.id.song_singer_name_tv)
        repeatButton = findViewById(R.id.song_repeat_iv)
        randomButton = findViewById(R.id.song_random_iv)
        playButton = findViewById(R.id.song_miniplayer_iv)
        pauseButton = findViewById(R.id.song_pause_iv)

        // MainActivity로부터 데이터 수신
        val songTitle = intent.getStringExtra("songTitle")
        val songArtist = intent.getStringExtra("songArtist")

        // 전달받은 데이터를 텍스트뷰에 설정
        songTitleTextView.text = songTitle
        songArtistTextView.text = songArtist

        // 버튼 리스너 설정
        setupRepeatButton()
        setupRandomButton()
        setupPlayPauseButtons()
    }

    private fun setupRepeatButton() {
        repeatButton.setOnClickListener {
            isRepeating = !isRepeating
            if (isRepeating) {
                repeatButton.setImageResource(R.drawable.nugu_btn_pause_32) // 활성화된 반복 재생 아이콘
            } else {
                repeatButton.setImageResource(R.drawable.nugu_btn_repeat_inactive) // 비활성화된 반복 재생 아이콘
            }
        }
    }

    private fun setupRandomButton() {
        randomButton.setOnClickListener {
            isRandom = !isRandom
            if (isRandom) {
                randomButton.setImageResource(R.drawable.nugu_btn_pause_32) // 활성화된 랜덤 재생 아이콘
            } else {
                randomButton.setImageResource(R.drawable.nugu_btn_random_inactive) // 비활성화된 랜덤 재생 아이콘
            }
        }
    }

    private fun setupPlayPauseButtons() {
        playButton.setOnClickListener {
            if (!isPlaying) {
                isPlaying = true
                playButton.visibility = ImageView.GONE
                pauseButton.visibility = ImageView.VISIBLE
            }
        }

        pauseButton.setOnClickListener {
            if (isPlaying) {
                isPlaying = false
                playButton.visibility = ImageView.VISIBLE
                pauseButton.visibility = ImageView.GONE
            }
        }
    }
}
