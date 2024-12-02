package com.example.songapp
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ProfileFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var songAdapter: SongAdapter
    private val songList = mutableListOf<Song>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = inflater.inflate(R.layout.fragment_profile, container, false)

        recyclerView = binding.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(context)
        songAdapter = SongAdapter(songList)
        recyclerView.adapter = songAdapter

        loadLikedSongs() // DB에서 좋아요 곡 로드

        return binding
    }

    fun loadLikedSongs() {
        val dbHelper = SongsDatabaseHelper(requireContext())
        val cursor = dbHelper.getAllSongs()
        songList.clear()

        while (cursor.moveToNext()) {
            val songId = cursor.getInt(cursor.getColumnIndexOrThrow("songId"))
            val title = cursor.getString(cursor.getColumnIndexOrThrow("title"))
            val artist = cursor.getString(cursor.getColumnIndexOrThrow("artist"))
            val imageResId = cursor.getInt(cursor.getColumnIndexOrThrow("imageResId"))
            val isLike = cursor.getInt(cursor.getColumnIndexOrThrow("isLike")) == 1

            if (isLike) {
                songList.add(Song(songId, title, artist, imageResId, isLike))
            }
        }
        cursor.close()
        songAdapter.notifyDataSetChanged()
    }
}



