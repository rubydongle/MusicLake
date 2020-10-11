package com.midas.music.ui.music.artist.contract

import com.midas.music.bean.Music
import com.midas.music.ui.base.BaseContract

interface ArtistSongContract {

    interface View : BaseContract.BaseView {

        fun showEmptyView()

        fun showSongs(songList: MutableList<Music>)
    }

    interface Presenter : BaseContract.BasePresenter<View> {

        fun loadSongs(artistName: String)
    }

}
