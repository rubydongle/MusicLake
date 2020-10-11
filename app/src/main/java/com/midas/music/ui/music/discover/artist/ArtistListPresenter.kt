package com.midas.music.ui.music.discover.artist

import com.cyl.musicapi.bean.Artists
import com.midas.music.api.music.MusicApiServiceImpl
import com.midas.music.ui.base.BasePresenter
import com.midas.music.bean.Artist
import com.midas.music.common.Constants
import com.midas.music.api.net.ApiManager
import com.midas.music.api.net.RequestCallBack
import javax.inject.Inject

/**
 * Des    :
 * Author : master.
 * Date   : 2018/5/20 .
 */
class ArtistListPresenter @Inject
constructor() : BasePresenter<ArtistListContract.View>(), ArtistListContract.Presenter {

    override fun loadArtists(offset: Int, params: Map<String, Int>) {
        mView?.showLoading()
        ApiManager.request(MusicApiServiceImpl.getArtists(offset, params), object : RequestCallBack<Artists> {
            override fun success(result: Artists) {
                val artists = mutableListOf<Artist>()
                result.singerList.forEach {
                    val artist = Artist().apply {
                        name = it.singer_name
                        artistId = it.singer_id
                        picUrl = it.singer_pic
                        type = Constants.QQ
                    }
                    artists.add(artist)
                }
                mView?.hideLoading()
                mView?.showArtistList(artists)
                mView?.showArtistTags(result.tags)
            }

            override fun error(msg: String) {
                mView?.hideLoading()
                mView?.showError(msg, true)
            }
        })
    }

}
