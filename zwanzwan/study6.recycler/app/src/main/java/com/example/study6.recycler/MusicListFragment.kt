//package com.example.study2real
//
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
//
//class MusicListFragment : Fragment() {
//
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
//    private lateinit var musicAdapter: MusicAdapter
//    private val musicList = mutableListOf<String>()
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view = inflater.inflate(R.layout.fragment_music_list, container, false)
//
//        recyclerView = view.findViewById(R.id.recycler_view)
//        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh)
//
//        // RecyclerView 설정
//        recyclerView.layoutManager = LinearLayoutManager(context)
//        musicAdapter = MusicAdapter(musicList)
//        recyclerView.adapter = musicAdapter
//
//        // 더미 데이터 추가
//        loadDummyData()
//
//        // 새로고침 기능 설정
//        swipeRefreshLayout.setOnRefreshListener {
//            loadDummyData()
//            swipeRefreshLayout.isRefreshing = false
//        }
//
//        return view
//    }
//
//    private fun loadDummyData() {
//        musicList.clear()
//        for (i in 1..50) {
//            musicList.add("Music Title $i")
//        }
//        musicAdapter.notifyDataSetChanged()
//    }
//}