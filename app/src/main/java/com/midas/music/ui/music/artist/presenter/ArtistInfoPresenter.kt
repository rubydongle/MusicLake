package com.midas.music.ui.music.artist.presenter

import com.midas.music.ui.base.BasePresenter
import com.midas.music.bean.Music
import com.midas.music.ui.music.artist.contract.ArtistInfoContract

import javax.inject.Inject

/**
 * Created by D22434 on 2018/1/4.
 */

class ArtistInfoPresenter @Inject
constructor() : BasePresenter<ArtistInfoContract.View>(), ArtistInfoContract.Presenter {

    override fun loadArtistInfo(music: Music) {
        val info = music.title + "-" + music.artist

    }
}
