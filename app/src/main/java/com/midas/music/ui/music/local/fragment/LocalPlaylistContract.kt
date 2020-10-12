package com.midas.music.ui.music.local.fragment

import com.midas.music.ui.base.BaseContract
import com.midas.music.bean.Music
import com.midas.music.bean.NoticeInfo
import com.midas.music.bean.Playlist

interface LocalPlaylistContract {

    interface View : BaseContract.BaseView {

//        fun showSongs(songList: MutableList<Music>)

//        fun showVideoList(videoList: MutableList<Music>)

        fun showLocalPlaylist(playlists: MutableList<Playlist>)

        fun showPlaylist(playlists: MutableList<Playlist>)

        fun showWyPlaylist(playlists: MutableList<Playlist>)

        fun showHistory(musicList: MutableList<Music>)

        fun showLoveList(musicList: MutableList<Music>)

//        fun showDownloadList(musicList: MutableList<Music>)

        fun showNoticeInfo(notice: NoticeInfo)
    }

    interface Presenter : BaseContract.BasePresenter<View> {
        fun loadSongs()

        fun loadPlaylist(playlist: Playlist? = null)
    }
}
