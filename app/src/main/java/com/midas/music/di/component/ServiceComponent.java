package com.midas.music.di.component;

import android.content.Context;


import com.midas.music.di.module.ServiceModule;
import com.midas.music.di.scope.ContextLife;
import com.midas.music.di.scope.PerService;

import dagger.Component;


/**
 * Created by lw on 2017/1/19.
 */
@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {
    @ContextLife("Service")
    Context getServiceContext();

    @ContextLife("Application")
    Context getApplicationContext();
}
