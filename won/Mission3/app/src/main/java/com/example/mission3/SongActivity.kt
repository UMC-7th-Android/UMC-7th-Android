package com.example.mission3

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.mission3.databinding.ActivitySongBinding

class SongActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySongBinding
    private lateinit var song: Song
    private var countDownTimer: CountDownTimer? = null
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySongBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initSong()
        setPlayer(song)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#FFFFFF")
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.songSettingIb.setOnClickListener {
            finish()
        }

        binding.playPauseButton.setOnClickListener {
            if (isPlaying) {
                pauseSong()
            } else {
                playSong()
            }
        }
    }

    private fun initSong() {
        if (intent.hasExtra("title") && intent.hasExtra("singer")) {
            song = Song(
                title = intent.getStringExtra("title") ?: return,
                singer = intent.getStringExtra("singer") ?: return,
                second = intent.getIntExtra("second", 0),
                playTime = intent.getIntExtra("playTime", 0),
                isPlaying = intent.getBooleanExtra("isPlaying", false)
            )
        } else {
            Log.e("SongActivity", "Intent extras are missing.")
            song = Song("Unknown Title", "Unknown Singer", 0, 191, false)
        }
    }

    private fun setPlayer(song: Song) {
        binding.songTitleTv.text = song.title
        binding.songSingerTv.text = song.singer
        binding.songStartTimeTv.text = String.format("%02d:%02d", song.second / 60, song.second % 60)
        binding.songEndTimeTv.text = String.format("%02d:%02d", song.playTime / 60, song.playTime % 60)

        binding.songProgressbarView.max = song.playTime * 1000
        binding.songProgressbarView.progress = song.second * 1000

        setPlayerStatus(song.isPlaying)
    }

    private fun setPlayerStatus(isPlaying: Boolean) {
        if (isPlaying) {
            binding.playPauseButton.setImageResource(R.drawable.baseline_pause_24)
            binding.songProgressbarView.progressTintList = ColorStateList.valueOf(Color.parseColor("#81BEF7"))
            startTimer() // 타이머 시작
        } else {
            binding.playPauseButton.setImageResource(R.drawable.baseline_play_24)
            binding.songProgressbarView.progressTintList = ColorStateList.valueOf(Color.GRAY)
        }
    }

    private fun startTimer() {
        countDownTimer = object : CountDownTimer(song.playTime * 1000L, 1000) {
            private var elapsedSeconds = song.second

            override fun onTick(millisUntilFinished: Long) {
                elapsedSeconds++
                binding.songProgressbarView.progress = (elapsedSeconds * 1000)

                binding.songStartTimeTv.text = String.format("%02d:%02d", elapsedSeconds / 60, elapsedSeconds % 60)
            }

            override fun onFinish() {
                binding.songProgressbarView.progress = binding.songProgressbarView.max
                pauseSong() // 재생 완료 후 일시정지 상태로 변경
            }
        }.start()
    }

    private fun playSong() {
        isPlaying = true
        setPlayerStatus(isPlaying)
    }

    private fun pauseSong() {
        isPlaying = false
        countDownTimer?.cancel()
        setPlayerStatus(isPlaying)
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer?.cancel()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}


