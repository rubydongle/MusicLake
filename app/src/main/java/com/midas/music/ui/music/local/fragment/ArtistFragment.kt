package com.midas.music.ui.music.local.fragment

import android.os.Bundle

import androidx.recyclerview.widget.StaggeredGridLayoutManager

import com.midas.music.R
import com.midas.music.ui.base.BaseLazyFragment
import com.midas.music.bean.Artist
import com.midas.music.ui.music.local.adapter.ArtistAdapter
import com.midas.music.ui.music.local.contract.ArtistContract
import com.midas.music.ui.music.local.presenter.ArtistPresenter

import java.util.ArrayList

import com.midas.music.event.FileEvent
import kotlinx.android.synthetic.main.fragment_recyclerview_notoolbar.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class ArtistFragment : BaseLazyFragment<ArtistPresenter>(), ArtistContract.View {

    private var mAdapter: ArtistAdapter? = null
    private val artists = ArrayList<Artist>()

    /**
     * 初始化视图
     *
     * @return
     */
    override fun getLayoutId(): Int {
        return R.layout.fragment_recyclerview_notoolbar
    }

    /**
     * 初始化控件
     */
    override fun initViews() {
        mAdapter = ArtistAdapter(artists)
        recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.adapter = mAdapter
        mAdapter?.bindToRecyclerView(recyclerView)
    }

    override fun initInjector() {
        mFragmentComponent.inject(this)
    }

    override fun listener() {}

    override fun onLazyLoad() {
        mPresenter?.loadArtists("all")
    }


    override fun showLoading() {
        super.showLoading()
    }

    override fun hideLoading() {
        super.hideLoading()
    }

    override fun showArtists(artists: List<Artist>) {
        mAdapter?.setNewData(artists)
        hideLoading()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun updateDownloadEvent(event: FileEvent) {
        mPresenter?.loadArtists("all")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventBus.getDefault().register(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventBus.getDefault().unregister(this)
    }

    override fun showEmptyView() {
        mAdapter?.setEmptyView(R.layout.view_song_empty, recyclerView)
    }

    companion object {

        fun newInstance(): ArtistFragment {
            val args = Bundle()
            val fragment = ArtistFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
