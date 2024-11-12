package com.example.mission5

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mission5.databinding.ItemDataBinding

class DataRVAdapter(
    private val dataList: ArrayList<Data>,
    val onClickDeleteBtn: (data: Data) -> Unit
) : RecyclerView.Adapter<DataRVAdapter.DataViewHolder>() {

    inner class DataViewHolder(val binding: ItemDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Data) {
            binding.tvMemo.text = data.memo // 메모 내용을 TextView에 설정
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val viewBinding = ItemDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val listposition = dataList[position]
        holder.bind(listposition)
        holder.binding.deleteBtn.setOnClickListener {
            onClickDeleteBtn.invoke(listposition) // 삭제 버튼 클릭 시 호출
        }
    }

    override fun getItemCount(): Int = dataList.size // 아이템 개수 반환
}
