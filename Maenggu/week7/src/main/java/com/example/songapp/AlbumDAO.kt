package com.example.songapp

import androidx.room.*

@Dao
interface AlbumDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlbum(album: Album)

    @Query("SELECT * FROM Album")
    fun getAllAlbums(): List<Album>

    @Query("SELECT * FROM Album WHERE id = :albumId")
    fun getAlbumById(albumId: Int): Album

    @Delete
    fun deleteAlbum(album: Album)
}