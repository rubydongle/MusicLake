package com.midas.music.ui.music.local.fragment;

import android.content.Context;
import android.content.Intent;

import com.midas.music.R;
import com.midas.music.ui.base.BaseActivity;
import com.midas.music.ui.base.BasePresenter;
import com.midas.music.common.Extras;
import com.midas.music.common.NavigationHelper;

/**
 * Created by Monkey on 2015/6/29.
 */
public class LocalVideoActivity extends BaseActivity<BasePresenter> {

    @Override
    protected int getLayoutResID() {
        return R.layout.include_main;
    }

    @Override
    protected String setToolbarTitle() {
        return getResources().getString(R.string.item_video);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {
        NavigationHelper.INSTANCE.navigateToVideo(this, null);
    }

    @Override
    protected void initInjector() {

    }

    public static void newInstance(Context context, String folderPath) {
        Intent intent = new Intent(context, LocalVideoActivity.class);
        intent.putExtra(Extras.FOLDER_PATH, folderPath);
        context.startActivity(intent);
    }
}
