package com.midas.music.ui.music.local.contract

import com.midas.music.ui.base.BaseContract
import com.midas.music.bean.Music

interface SongsContract {

    interface View : BaseContract.BaseView {
        fun showSongs(songList: MutableList<Music>)

        fun setEmptyView()
    }

    interface Presenter : BaseContract.BasePresenter<View> {
        fun loadSongs(isReload: Boolean)
    }
}
