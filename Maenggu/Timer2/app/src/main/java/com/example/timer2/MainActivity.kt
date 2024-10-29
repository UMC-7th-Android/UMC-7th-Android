package com.example.timer2



import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import com.example.timer2.databinding.ActivityMainBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var time = 0L // 밀리초 단위로 변경
    private var isRunning = false
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startPauseButton.setOnClickListener { toggleStartPause() }
        binding.clearButton.setOnClickListener { clearTimer() }
    }

    private fun toggleStartPause() {
        if (!isRunning) {
            startTimer()
            binding.startPauseButton.text = "Pause"
        } else {
            pauseTimer()
            binding.startPauseButton.text = "Start"
        }
    }

    private fun startTimer() {
        isRunning = true
        job = CoroutineScope(Dispatchers.Main).launch {
            while (isRunning) {
                delay(10) // 10 밀리초마다 업데이트
                time += 10 // 10 밀리초 증가
                binding.timerText.text = formatTime(time) // 포맷 변경
            }
        }
    }

    private fun pauseTimer() {
        isRunning = false
        job?.cancel()
    }

    private fun clearTimer() {
        isRunning = false
        time = 0
        binding.timerText.text = formatTime(time)
        job?.cancel()
        binding.startPauseButton.text = "Start"
    }

    private fun formatTime(milliseconds: Long): String {
        val seconds = (milliseconds / 1000) % 60
        val minutes = (milliseconds / 1000) / 60
        val millis = (milliseconds % 1000) / 10 // 밀리초를 0.00 형식으로 변환
        return String.format("%02d:%02d.%02d", minutes, seconds, millis)
    }
}
