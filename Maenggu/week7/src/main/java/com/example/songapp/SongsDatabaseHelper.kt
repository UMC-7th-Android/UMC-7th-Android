package com.example.songapp

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SongsDatabaseHelper(context: Context) : SQLiteOpenHelper(context, "SongsDB", null, 3) {  // 버전 3으로 업데이트

    companion object {
        private const val DATABASE_NAME = "SongsDB"
        private const val DATABASE_VERSION = 3  // DB 버전 3
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = """
            CREATE TABLE songs (
                songId INTEGER PRIMARY KEY AUTOINCREMENT,
                title TEXT NOT NULL,
                artist TEXT NOT NULL,
                imageResId INTEGER NOT NULL,
                isLike INTEGER NOT NULL DEFAULT 0  -- 'isLike' 컬럼 추가, 기본값 0
            )
        """.trimIndent()
        db.execSQL(createTableQuery)
    }

    // 버전 3으로 업그레이드 시 'isLike' 컬럼 추가
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        if (oldVersion < 3) {
            // 'isLike' 컬럼을 추가
            val alterTableQuery = "ALTER TABLE songs ADD COLUMN isLike INTEGER NOT NULL DEFAULT 0"
            db.execSQL(alterTableQuery)
        }
    }

    // 모든 곡 삭제
    fun clearAllSongs() {
        val db = writableDatabase
        db.execSQL("DELETE FROM songs")
        db.close()
    }

    // 곡 삽입
    fun insertSong(title: String, artist: String, imageResId: Int): Long {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("title", title)
            put("artist", artist)
            put("imageResId", imageResId)  // imageResId 추가
            put("isLike", 0)  // 기본값은 0 (좋아요 아님)
        }
        return db.insert("songs", null, values)
    }

    // 좋아요 상태 업데이트
    fun updateSongLikeStatus(songId: Int, isLike: Boolean) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("isLike", if (isLike) 1 else 0)  // 1이면 좋아요, 0이면 아니오
        }

        db.update("songs", values, "songId = ?", arrayOf(songId.toString()))
        db.close()
    }

    // songId로 곡 검색
    fun getSongById(songId: Int): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM songs WHERE songId = ?", arrayOf(songId.toString()))
    }

    // 모든 곡 가져오기
    fun getAllSongs(): Cursor {
        val db = readableDatabase
        return db.rawQuery("SELECT * FROM songs", null)
    }
}



