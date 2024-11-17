package com.example.mission6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private val imageList: List<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    // 뷰 타입 정의: 첫 번째 아이템에만 버튼을 포함
    private val VIEW_TYPE_BUTTONS = 0
    private val VIEW_TYPE_IMAGE = 1

    // ViewHolder 클래스 - 버튼 포함한 첫 번째 항목
    class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button1: Button = itemView.findViewById(R.id.btn1)
        val button2: Button = itemView.findViewById(R.id.btn2)
        val button3: Button = itemView.findViewById(R.id.btn3)
        val button4: Button = itemView.findViewById(R.id.btn4)
    }

    // ViewHolder 클래스 - 일반 이미지 항목
    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view)
    }

    // 뷰 타입에 따라 ViewHolder 생성
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_BUTTONS) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_buttons, parent, false)
            ButtonViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false)
            ImageViewHolder(view)
        }
    }

    // 데이터 바인딩
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ImageViewHolder) {
            // 일반 이미지 항목에 이미지 설정
            val imageResId = imageList[position - 1] // 첫 번째 항목은 버튼이므로 인덱스 조정
            holder.imageView.setImageResource(imageResId)
        }
    }

    // 아이템 개수 반환
    override fun getItemCount(): Int {
        return imageList.size + 1 // 첫 번째 버튼 항목 추가로 +1
    }

    // 아이템의 뷰 타입 결정
    override fun getItemViewType(position: Int): Int {
        return if (position == 0) VIEW_TYPE_BUTTONS else VIEW_TYPE_IMAGE
    }
}


