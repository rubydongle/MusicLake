package com.midas.music.di.component;

import android.app.Activity;
import android.content.Context;

import com.midas.music.di.module.ActivityModule;
import com.midas.music.di.scope.ContextLife;
import com.midas.music.di.scope.PerActivity;
import com.midas.music.ui.chat.ChatActivity;
import com.midas.music.ui.chat.ChatDetailActivity;
import com.midas.music.ui.music.artist.activity.ArtistDetailActivity;
import com.midas.music.ui.music.charts.activity.BaiduMusicListActivity;
import com.midas.music.ui.music.charts.activity.BasePlaylistActivity;
import com.midas.music.ui.music.edit.EditSongListActivity;
import com.midas.music.ui.music.mv.VideoDetailActivity;
import com.midas.music.ui.music.mv.VideoPlayerActivity;
import com.midas.music.ui.music.playlist.detail.PlaylistDetailActivity;
import com.midas.music.ui.music.playpage.LockScreenPlayerActivity;
import com.midas.music.ui.music.playpage.PlayerActivity;
import com.midas.music.ui.music.search.SearchActivity;
import com.midas.music.ui.my.BindLoginActivity;
import com.midas.music.ui.my.LoginActivity;
import com.midas.music.ui.my.RegisterActivity;

import org.jetbrains.annotations.NotNull;

import dagger.Component;

/**
 * Created by lw on 2017/1/19.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    @ContextLife("Activity")
    Context getActivityContext();

    @ContextLife("Application")
    Context getApplicationContext();

    Activity getActivity();

    void inject(BaiduMusicListActivity baiduMusicListActivity);


    void inject(RegisterActivity registerActivity);

    void inject(LoginActivity loginActivity);

    void inject(SearchActivity searchActivity);

    void inject(BasePlaylistActivity basePlaylistActivity);

//    void inject(VideoDetailActivity videoDetailActivity);
//
    void inject(@NotNull PlayerActivity playerActivity);

    void inject(PlaylistDetailActivity playlistDetailActivity);

    void inject(ArtistDetailActivity playlistDetailActivity);

    void inject(EditSongListActivity editMusicActivity);

    void inject(@NotNull ChatActivity chatActivity);

    void inject(@NotNull ChatDetailActivity chatDetailActivity);

    void inject(@NotNull VideoPlayerActivity baiduMvDetailActivity);

    void inject(@NotNull LockScreenPlayerActivity lockScreenPlayerActivity);

    void inject(@NotNull BindLoginActivity bindLoginActivity);

    void inject(@NotNull VideoDetailActivity videoDetailActivity);
}
