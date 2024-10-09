package com.example.project_1

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class UserFragment {companion object{
    const val TAG : String = "로그"
    fun newInstance() : UserFragment {
        return UserFragment()
    }
}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "UserFragment - onCreate() called")
    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        Log.d(TAG, "UserFragment - onAttach() called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "UserFragment - onCreateView() called")
        val view =  inflater.inflate(R.layout.fragment_user, container, false)
        return view
    }
}
