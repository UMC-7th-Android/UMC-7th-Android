package com.example.study2real

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.fragment.app.FragmentManager

class AlbumAdapter(
    private val fragmentManager: FragmentManager
) : RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder>() {

    private val musicList = listOf(
        MusicItem(R.drawable.album_image1, "Tokyo Flash"),
        MusicItem(R.drawable.album_image2, "Odori"),
        MusicItem(R.drawable.album_image3, "Shiwaawase"),
        MusicItem(R.drawable.album_image4, "Fukakouryoku"),
        MusicItem(R.drawable.album_image5, "Kaiju no Hanauta"),
        MusicItem(R.drawable.album_image1, "Benefit"),
        MusicItem(R.drawable.album_image5, "Sorafune")

    )

    inner class AlbumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val albumImage: ImageView = itemView.findViewById(R.id.albumImage)
        val albumTitle: TextView = itemView.findViewById(R.id.albumTitle)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_music, parent, false)
        return AlbumViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        val musicItem = musicList[position]
        holder.albumImage.setImageResource(musicItem.imageResId)  // 앨범 이미지 설정
        holder.albumTitle.text = musicItem.title  // 앨범 제목 설정


    }

    override fun getItemCount(): Int {
        return musicList.size
    }
}
