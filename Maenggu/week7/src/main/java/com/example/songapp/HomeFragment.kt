package com.example.songapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.room.Room
import androidx.viewpager2.widget.ViewPager2


class HomeFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var bannerAdapter: BannerAdapter

    // 데이터베이스 DAO 초기화
    private lateinit var albumDAO: AlbumDAO
    private lateinit var songDAO: SongDAO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // ViewPager2 초기화
        viewPager = view.findViewById(R.id.banner_viewpager)

        // 배너 데이터
        val bannerImages = listOf(
            R.drawable.banner1, // drawable 리소스 ID
            R.drawable.banner3,
            R.drawable.banner2
        )

        // Adapter 연결
        bannerAdapter = BannerAdapter(bannerImages)
        viewPager.adapter = bannerAdapter

        // 기존 텍스트 설정 유지
        view.findViewById<TextView>(R.id.text_view).text = "홈 화면"

        // 데이터베이스 초기화
        val db = Room.databaseBuilder(
            requireContext(),
            AppDatabase::class.java,
            "music_database"
        ).allowMainThreadQueries().build()

        albumDAO = db.albumDAO()
        songDAO = db.songDAO()

        // 예제 데이터 삽입
        insertExampleData()

        // 데이터 가져오기 및 출력
        fetchAndDisplayData()

        return view
    }

    private fun insertExampleData() {
        // 앨범 데이터 삽입
        val album1 = Album(1, "Butter", "BTS", "cover_butter.jpg")
        val album2 = Album(2, "Next Level", "aespa", "cover_next_level.jpg")
        albumDAO.insertAlbum(album1)
        albumDAO.insertAlbum(album2)

        // 노래 데이터 삽입
        val song1 = Song(1, "Butter", "BTS", 180, true, false, "butter.mp3", "cover_butter.jpg", 1)
        val song2 = Song(2, "Next Level", "aespa", 200, false, true, "next_level.mp3", "cover_next_level.jpg", 2)
        songDAO.insertSong(song1)
        songDAO.insertSong(song2)
    }

    private fun fetchAndDisplayData() {
        // 앨범 데이터 가져오기
        val albums = albumDAO.getAllAlbums()
        Log.d("HomeFragment", "Albums:")
        for (album in albums) {
            Log.d("HomeFragment", "Album: ${album.title}, Singer: ${album.singer}")
        }

        // 노래 데이터 가져오기
        val songs = songDAO.getAllSongs()
        Log.d("HomeFragment", "Songs:")
        for (song in songs) {
            Log.d("HomeFragment", "Song: ${song.title}, Album ID: ${song.albumIdx}")
        }
    }
}
