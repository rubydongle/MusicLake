package com.midas.music.ui.music.local.contract;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.midas.music.ui.base.BaseContract;
import com.midas.music.bean.Music;

import java.util.List;


public interface AlbumDetailContract {

    interface View extends BaseContract.BaseView {

        void showEmptyView();

        void showAlbumSongs(List<Music> songList);

        void showAlbumArt(Drawable albumArt);

        void showAlbumArt(Bitmap bitmap);
    }

    interface Presenter extends BaseContract.BasePresenter<View> {
        void loadAlbumSongs(String albumName);
    }
}
