package com.example.project_1

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class EditFragment {
    companion object {
        const val TAG: String = "로그"
        fun newInstance(): EditFragment {
            return EditFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "EditFragment - onCreate() called")
    }

    override fun onAttach(context: Context){
        super.onAttach(context)
        Log.d(TAG, "EditFragment - onAttach() called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "EditFragment - onCreateView() called")
        val view =  inflater.inflate(R.layout.fragment_edit, container, false)
        return view
    }
}
}