package com.example.songapp
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.appname.model.Song
import com.example.appname.model.Album
import com.example.appname.dao.SongDAO
import com.example.appname.dao.AlbumDAO

@Database(entities = [Song::class, Album::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun songDAO(): SongDAO
    abstract fun albumDAO(): AlbumDAO
}