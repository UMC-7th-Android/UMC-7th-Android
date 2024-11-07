package com.example.study5

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var editTextMemo: EditText
    private lateinit var buttonNext: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var memoAdapter: MemoAdapter
    private var memoList = ArrayList<String>() // 메모 내용을 저장할 리스트

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextMemo = findViewById(R.id.editTextMemo)
        buttonNext = findViewById(R.id.buttonNext)
        recyclerView = findViewById(R.id.recyclerView)

        // RecyclerView 설정
        recyclerView.layoutManager = LinearLayoutManager(this)
        memoAdapter = MemoAdapter(memoList)
        recyclerView.adapter = memoAdapter

        // 확인 화면으로 이동
        buttonNext.setOnClickListener {
            val memo = editTextMemo.text.toString()
            if (memo.isNotEmpty()) {
                memoList.add(memo) // 메모를 리스트에 추가
                memoAdapter.notifyDataSetChanged() // RecyclerView 갱신

                val intent = Intent(this, ConfirmActivity::class.java)
                intent.putExtra("memo", memo)
                startActivity(intent)

                // 입력 필드 비우기
                editTextMemo.text.clear()
            } else {
                AlertDialog.Builder(this)
                    .setTitle("오류")
                    .setMessage("메모를 입력해주세요.")
                    .setPositiveButton("확인", null)
                    .show()
            }
        }
    }

    override fun onRestart() {
        super.onRestart()
        // 다시 작성할지 여부를 묻는 Dialog
        AlertDialog.Builder(this)
            .setTitle("다시 작성하시겠습니까?")
            .setMessage("이전 메모가 사라집니다.")
            .setPositiveButton("예") { _, _ ->
                memoList.clear() // 모든 메모 삭제
                memoAdapter.notifyDataSetChanged() // RecyclerView 갱신
                editTextMemo.text.clear() // 입력 필드 비우기
            }
            .setNegativeButton("아니요", null)
            .show()
    }
}
