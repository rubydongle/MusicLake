package com.midas.music.di.module;

import android.content.Context;


import com.midas.music.MusicApp;
import com.midas.music.di.scope.ContextLife;
import com.midas.music.di.scope.PerApp;

import dagger.Module;
import dagger.Provides;


/**
 * Created by lw on 2017/1/19.
 */
@Module
public class ApplicationModule {
    private MusicApp mApplication;

    public ApplicationModule(MusicApp application) {
        mApplication = application;
    }

    @Provides
    @PerApp
    @ContextLife("Application")
    public Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }
}
