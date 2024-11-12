package com.example.study5

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MemoAdapter(private val memoList: ArrayList<String>) : RecyclerView.Adapter<MemoAdapter.MemoViewHolder>() {

    class MemoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewMemo: TextView = itemView.findViewById(R.id.textViewMemo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.memo_item, parent, false)
        return MemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        holder.textViewMemo.text = memoList[position]
    }

    override fun getItemCount(): Int {
        return memoList.size
    }
}
