package com.example.thirdstudy

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class LibFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fragment_home 레이아웃을 이 프래그먼트에 연결
        return inflater.inflate(R.layout.lib_fragment, container, false)
    }
}