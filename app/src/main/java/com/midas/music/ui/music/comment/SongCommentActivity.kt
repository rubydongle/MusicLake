package com.midas.music.ui.music.comment

import com.cyl.musicapi.bean.SongComment
import com.midas.music.R
import com.midas.music.api.music.MusicApi
import com.midas.music.ui.base.BaseActivity
import com.midas.music.ui.base.BaseContract
import com.midas.music.ui.base.BasePresenter
import com.midas.music.bean.Music
import com.midas.music.common.Extras
import kotlinx.android.synthetic.main.activity_song_comment.*

/**
 * Des    :
 * Author : master.
 * Date   : 2018/9/8 .
 */
class SongCommentActivity : BaseActivity<BasePresenter<BaseContract.BaseView>>() {

    private var commentAdapter: SongCommentAdapter? = null

    override fun getLayoutResID(): Int {
        return R.layout.activity_song_comment
    }

    override fun setToolbarTitle(): String {
        return getString(R.string.song_comment_new)
    }

    override fun initView() {
//        hotCommentRsv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        newCommentRsv.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
    }

    override fun initData() {
        val music = intent.getParcelableExtra<Music>(Extras.SONG)
        music?.let {
            MusicApi.getMusicCommentInfo(it, success = {
            updateSongComment(it)
        }, fail = {
            showError("未搜索到当前歌曲评论", false)
        })
        }
    }

    private fun updateSongComment(comments: MutableList<SongComment>?) {
        if (comments == null) {
            showError("未搜索到当前歌曲评论", false)
            return
        }
        if (commentAdapter == null) {
            commentAdapter = SongCommentAdapter(comments)
            newCommentRsv.adapter = commentAdapter
        } else {
            commentAdapter?.setNewData(comments)
        }
    }

    override fun initInjector() {
    }
}