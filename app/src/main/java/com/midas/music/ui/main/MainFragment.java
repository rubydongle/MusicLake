package com.midas.music.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.midas.music.R;
import com.midas.music.ui.base.BaseFragment;
import com.midas.music.ui.music.local.fragment.AlbumFragment;
import com.midas.music.ui.music.local.fragment.ArtistFragment;
import com.midas.music.ui.music.local.fragment.FoldersFragment;
import com.midas.music.ui.music.local.fragment.LocalVideoFragment;
import com.midas.music.ui.music.local.fragment.SongsFragment;
import com.midas.music.ui.music.local.fragment.LocalPlaylistFragment;
import com.google.android.material.tabs.TabLayout;
import com.midas.music.ui.music.my.MyMusicFragment;

import butterknife.BindView;

/**
 * 作者：yonglong on 2016/8/8 17:47
 * 邮箱：643872807@qq.com
 * 版本：2.5
 */
@SuppressWarnings("ConstantConditions")
public class MainFragment extends BaseFragment {
    @BindView(R.id.m_viewpager)
    ViewPager mViewPager;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    public static MainFragment newInstance() {
        Bundle args = new Bundle();
        MainFragment fragment = new MainFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void loadData() {

    }

    @Override
    public int getLayoutId() {
        return R.layout.frag_main;
    }

    @Override
    public void initViews() {
        if (getActivity() != null) {
            mToolbar.setTitle(R.string.app_name);
            AppCompatActivity appCompatActivity = (AppCompatActivity) getActivity();
            appCompatActivity.setSupportActionBar(mToolbar);
            final ActionBar toggle = appCompatActivity.getSupportActionBar();
            if (toggle != null) {
                toggle.setHomeAsUpIndicator(R.drawable.ic_menu_white);
                toggle.setDisplayHomeAsUpEnabled(true);
            }
        }
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
        setupViewPager(mViewPager);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                if (position == 3) {
//                    mTabLayout.setVisibility(View.GONE);
//                    mToolbar.setTitle("音乐MV");
//                } else {
//                    mTabLayout.setVisibility(View.VISIBLE);
//                    mToolbar.setTitle("音乐湖");
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    protected void initInjector() {

    }


    private void setupViewPager(ViewPager mViewPager) {
        PageAdapter mAdapter = new PageAdapter(getChildFragmentManager());
        mAdapter.addFragment(SongsFragment.Companion.newInstance(), getContext().getString(R.string.local_title));
        mAdapter.addFragment(AlbumFragment.Companion.newInstance(), getContext().getString(R.string.album_title));
        mAdapter.addFragment(ArtistFragment.Companion.newInstance(), getContext().getString(R.string.artist_title));
        mAdapter.addFragment(LocalPlaylistFragment.Companion.newInstance(), getContext().getString(R.string.playlist_title));
//        mAdapter.addFragment(LocalVideoFragment.Companion.newInstance(), getContext().getString(R.string.video_title));

//        mAdapter.addFragment(MyMusicFragment.Companion.newInstance(), getContext().getString(R.string.my));
//        mAdapter.addFragment(DiscoverFragment.Companion.newInstance(), getContext().getString(R.string.discover));
//        mAdapter.addFragment(ChartsDetailFragment.Companion.newInstance(), getContext().getString(R.string.charts));
//        mAdapter.addFragment(VideoSquareFragment.Companion.newInstance(), getContext().getString(R.string.video_title));
        mAdapter.addFragment(FoldersFragment.Companion.newInstance(), getString(R.string.folder_title));
        mViewPager.setAdapter(mAdapter);
    }

}