package com.example.mission5

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.mission5.databinding.ActivityMemoBinding

class MemoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMemoBinding
    private var savedMemo: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMemoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStore.setOnClickListener {
            val memoText = binding.editText.text.toString()
            val resultIntent = Intent().apply {
                putExtra("data", memoText)
            }
            setResult(RESULT_OK, resultIntent)
            finish()
        }

        //상태 표시줄 색상 변경
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#FAFAFA")
        }
    }

    override fun onResume() {
        super.onResume()
        savedMemo?.let {
            binding.editText.setText(it)
        }
    }

    override fun onPause() {
        super.onPause()
        savedMemo = binding.editText.text.toString()
    }

    override fun onRestart() {
        super.onRestart()
        AlertDialog.Builder(this).apply {
            setTitle("작성 확인")
            setMessage("다시 작성하시겠습니까?")
            setPositiveButton("네") { _, _ -> }
            setNegativeButton("아니요") { _, _ -> savedMemo = null }
        }.show()
    }
}


