package com.midas.music.data.db

import android.database.Cursor
import android.database.CursorWrapper

import com.midas.music.bean.Album
import com.midas.music.bean.Artist

class MusicCursorWrapper(cursor: Cursor) : CursorWrapper(cursor) {
    
    val album: Album
        get() {
            val id = getString(getColumnIndex("albumid"))
            val name = getString(getColumnIndex("album"))
            val artistId = getLong(getColumnIndex("artistid"))
            val artist = getString(getColumnIndex("artist"))
            val num = getInt(getColumnIndex("num"))
            val coverUri = getString(getColumnIndex("coveruri"))
            return Album(id, name, artist, artistId, num, coverUri)
        }

    val artists: Artist
        get() {
            val id = getLong(getColumnIndex("artistid"))
            val name = getString(getColumnIndex("artist"))
            val num = getInt(getColumnIndex("num"))
            val coverUri = getString(getColumnIndex("coveruri"))
            return Artist(id, name, num, coverUri)
        }

}
