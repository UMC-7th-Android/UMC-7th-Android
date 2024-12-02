package com.example.songapp

data class Song(
    val songId: Int,
    val title: String,
    val artist: String,
    val imageResId: Int,
    var isLike: Boolean  // 이 값은 변경 가능하므로 var로 설정
    val duration: Int,    // Duration in seconds
    val albumIdx: Int     // Foreign Key (Album ID)
)
