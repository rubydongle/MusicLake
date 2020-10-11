package com.midas.music.ui.music.mv

import com.cyl.musicapi.netease.CommentsItemInfo
import com.midas.music.bean.MvInfoBean
import com.midas.music.bean.VideoInfoBean
import com.midas.music.ui.base.BaseContract
import com.midas.music.ui.base.BaseContract.BaseView

interface VideoDetailContract {
    interface View : BaseView {
        fun showVideoInfoList(mvList: List<VideoInfoBean>)
        fun showBaiduMvDetailInfo(mvInfoBean: MvInfoBean?)
        fun showMvDetailInfo(mvInfoDetailInfo: VideoInfoBean?)
        fun showMvUrlInfo(mvUrl: String?)
        fun showMvHotComment(mvHotCommentInfo: List<CommentsItemInfo>)
        fun showMvComment(mvCommentInfo: List<CommentsItemInfo>)
    }

    interface Presenter : BaseContract.BasePresenter<View?> {
        fun loadMv(offset: Int)
        fun loadMvDetail(mvid: String?, type: Int)
        fun loadBaiduMvInfo(songId: String?)
        fun loadSimilarMv(mvid: String?, type: Int)
        fun loadMvComment(mvid: String?, type: Int, offset: Int)
    }
}