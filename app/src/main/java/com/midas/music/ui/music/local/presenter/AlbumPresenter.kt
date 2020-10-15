package com.midas.music.ui.music.local.presenter

import com.midas.music.data.SongLoader
import com.midas.music.ui.base.BasePresenter
import com.midas.music.ui.music.local.contract.AlbumsContract
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import javax.inject.Inject


/**
 * Created by yonglong on 2018/1/7.
 */

class AlbumPresenter @Inject
constructor() : BasePresenter<AlbumsContract.View>(), AlbumsContract.Presenter {
    override fun loadAlbums(action: String, isReload: Boolean) {
        mView?.showLoading()
        doAsync {
//            val data = SongLoader.getAllAlbums()
            val data = SongLoader.getLocalAlbums(mView.context, isReload)
            uiThread {
                mView?.showAlbums(data)
                mView?.hideLoading()
            }
        }
    }
}
