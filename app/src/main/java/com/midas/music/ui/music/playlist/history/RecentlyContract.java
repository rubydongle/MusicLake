package com.midas.music.ui.music.playlist.history;

import com.midas.music.bean.Music;
import com.midas.music.ui.base.BaseContract;

import java.util.List;

public interface RecentlyContract {

    interface View extends BaseContract.BaseView {

        void showSongs(List<Music> songs);

        void showEmptyView();

    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void loadSongs();

        void clearHistory();
    }
}
