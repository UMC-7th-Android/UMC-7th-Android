// src/main/java/com/example/thirdstudy/SearchFragment.kt
package com.example.thirdstudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.*

class SearchFragment : Fragment() {

    private lateinit var timeTextView: TextView
    private lateinit var startButton: Button
    private lateinit var pauseButton: Button
    private lateinit var clearButton: Button

    private var time = 0 // 시간 값, 초 단위로 증가
    private var isRunning = false // 타이머 실행 상태 관리
    private var job: Job? = null // Coroutine Job

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // search_fragment.xml을 이 프래그먼트에 연결
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // View 초기화
        timeTextView = view.findViewById(R.id.timeTextView)
        startButton = view.findViewById(R.id.startButton)
        pauseButton = view.findViewById(R.id.pauseButton)
        clearButton = view.findViewById(R.id.clearButton)

        //뷰 바인딩 추천

        // Start 버튼 클릭 리스너
        startButton.setOnClickListener {
            if (!isRunning) {
                startTimer()
            }
        }

        // Pause 버튼 클릭 리스너
        pauseButton.setOnClickListener {
            pauseTimer()
        }

        // Clear 버튼 클릭 리스너
        clearButton.setOnClickListener {
            clearTimer()
        }
    }

    // 타이머 시작
    private fun startTimer() {
        isRunning = true
        job = CoroutineScope(Dispatchers.Main).launch {
            while (isRunning) {
                delay(1000)
                time++
                updateDisplayedTime()
            }
        }
    }

    // 타이머 일시 정지
    private fun pauseTimer() {
        isRunning = false
        job?.cancel()
    }

    // 타이머 초기화
    private fun clearTimer() {
        if (isRunning) {
            // 타이머가 실행 중일 때는 정지하지 않고 시간만 0으로 설정
            time = 0
            updateDisplayedTime()
        } else {
            // 타이머가 일시정지 상태일 때는 초기화하고 정지 상태 유지
            time = 0
            updateDisplayedTime()
            job?.cancel()
        }
    }

    // 시간 업데이트 및 표시
    private fun updateDisplayedTime() {
        val minutes = time / 60
        val seconds = time % 60
        timeTextView.text = String.format("%02d:%02d", minutes, seconds)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job?.cancel() // Fragment가 파괴될 때 Coroutine 정지
    }
}
