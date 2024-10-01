package com.example.hsu_umc_android_7th

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


class HappyFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // fragment_excited 레이아웃을 inflate하여 반환
        return inflater.inflate(R.layout.fragment_happy, container, false)
    }
}
