package com.example.mission5

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mission5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val dataList: ArrayList<Data> = arrayListOf()
    private val dataRVAdapter = DataRVAdapter(dataList, onClickDeleteBtn = {
        deleteTask(it)
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvData.adapter = dataRVAdapter
        binding.rvData.layoutManager = LinearLayoutManager(this)

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this, MemoActivity::class.java)
            startActivityForResult(intent, 100)
        }

        //상태 표시줄 색상 변경
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#FAFAFA")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val memoText = data?.getStringExtra("data")
            if (!memoText.isNullOrEmpty()) {
                dataList.add(Data(memoText))
                dataRVAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun deleteTask(data: Data) {
        dataList.remove(data)
        dataRVAdapter.notifyDataSetChanged()
    }
}


