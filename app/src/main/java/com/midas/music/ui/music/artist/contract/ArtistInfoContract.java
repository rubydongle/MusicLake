package com.midas.music.ui.music.artist.contract;


import com.midas.music.bean.Music;
import com.midas.music.ui.base.BaseContract;

public interface ArtistInfoContract {

    interface View extends BaseContract.BaseView {

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void loadArtistInfo(Music music);
    }

}
