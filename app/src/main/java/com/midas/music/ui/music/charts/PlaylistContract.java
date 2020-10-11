package com.midas.music.ui.music.charts;


import com.midas.music.ui.base.BaseContract;
import com.midas.music.bean.Music;
import com.midas.music.bean.Playlist;

import java.util.List;

public interface PlaylistContract {

    interface View extends BaseContract.BaseView {

        void showPlayList(Playlist playlist);

        void showOnlineMusicList(List<Music> musicList);

        void showNeteaseCharts(List<Playlist> playlistList);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {

        void loadPlaylist(String idx, String type);

        void loadNeteasePlaylist(String id);

        void loadOnlineMusicList(String type, int limit, int mOffset);
    }
}
