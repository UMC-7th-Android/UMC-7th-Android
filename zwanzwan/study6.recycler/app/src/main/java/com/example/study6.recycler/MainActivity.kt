package com.example.study2real

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: AlbumAdapter
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // BottomNavigationView 초기화
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)

        // RecyclerView 초기화
        recyclerView = findViewById(R.id.recyclerView1)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // 어댑터 설정 및 데이터 초기화
        adapter = AlbumAdapter(supportFragmentManager)
        recyclerView.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(
            recyclerView.context,
            DividerItemDecoration.VERTICAL
        )
        dividerItemDecoration.setDrawable(
            ContextCompat.getDrawable(this, R.drawable.divider)!!
        )
        recyclerView.addItemDecoration(dividerItemDecoration)
    }
}
