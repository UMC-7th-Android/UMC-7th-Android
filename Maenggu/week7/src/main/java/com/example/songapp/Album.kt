package com.example.songapp

data class Album(
    val id: Int,        // Primary Key
    val title: String,  // Album Title
    val artist: String, // Album Artist
    val cover: String   // Album Cover (Image URL or Path)
)
