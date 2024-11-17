package com.example.mission6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mission6.databinding.Frag1Binding

class Frag1 : Fragment() {

    private var _binding: Frag1Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Fragment의 ViewBinding 초기화
        _binding = Frag1Binding.inflate(inflater, container, false)

        // RecyclerView 설정
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext()) // LinearLayoutManager 설정

        // 이미지 리스트 (예시 이미지 ID들)
        val imageList = listOf(
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
            R.drawable.pic4, R.drawable.pic5, R.drawable.pic6
        )

        val adapter = RecyclerViewAdapter(imageList)
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Fragment의 뷰가 파괴되었을 때 binding 객체 해제
        _binding = null
    }
}
