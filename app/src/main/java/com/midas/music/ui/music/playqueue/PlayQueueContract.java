package com.midas.music.ui.music.playqueue;

import com.midas.music.ui.base.BaseContract;
import com.midas.music.bean.Music;

import java.util.List;

public interface PlayQueueContract {

    interface View extends BaseContract.BaseView {
        void showSongs(List<Music> songs);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void loadSongs();

        void clearQueue();
    }
}
