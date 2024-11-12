package com.example.memo2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var editText: EditText
    private var memoContent: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editText)

        // 버튼 클릭 이벤트 처리
        findViewById<Button>(R.id.button).setOnClickListener {
            val intent = Intent(this, ConfirmActivity::class.java)
            intent.putExtra("memo", memoContent)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        if (memoContent.isNotEmpty()) {
            editText.setText(memoContent)
        }
    }

    override fun onPause() {
        super.onPause()
        memoContent = editText.text.toString()
    }

    override fun onRestart() {
        super.onRestart()
        AlertDialog.Builder(this)
            .setTitle("메모")
            .setMessage("다시 작성하시겠습니까?")
            .setPositiveButton("예") { _, _ ->
                // 아무 작업 없이 기존 내용 유지
            }
            .setNegativeButton("아니오") { _, _ ->
                memoContent = ""
            }
            .show()
    }
}