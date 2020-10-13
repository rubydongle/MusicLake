package com.midas.music.ui.music.player.fragment

import com.midas.music.R
import com.midas.music.player.FloatLyricViewManager
import com.midas.music.player.PlayManager
import com.midas.music.ui.base.BaseContract
import com.midas.music.ui.base.BaseLazyFragment
import com.midas.music.ui.base.BasePresenter
import com.midas.music.ui.widget.LyricView
import com.midas.music.utils.SPUtils
import kotlinx.android.synthetic.main.frag_player_lrcview.*

class LyricFragment : BaseLazyFragment<BasePresenter<BaseContract.BaseView>>() {

    val lyricTv by lazy { rootView?.findViewById<LyricView>(R.id.lyricShow) }

    override fun getLayoutId(): Int {
        return R.layout.frag_player_lrcview
    }

    override fun initInjector() {
    }
    /**
     *显示歌词
     */
    fun showLyric(lyric: String?, init: Boolean) {
        if (init) {
            //初始化歌词配置
            lyricShow?.setTextSize(SPUtils.getFontSize())
            lyricShow?.setHighLightTextColor(SPUtils.getFontColor())
            lyricShow?.setTouchable(true)
            lyricShow?.setOnPlayerClickListener { progress, _ ->
                PlayManager.seekTo(progress.toInt())
                if (!PlayManager.isPlaying()) {
                    PlayManager.playPause()
                }
            }
        }
        lyricShow?.setLyricContent(lyric)
    }

    fun setCurrentTimeMillis(current: Long = 0) {
        lyricShow?.setCurrentTimeMillis(current)
    }

    override fun onLazyLoad() {
        showLyric(FloatLyricViewManager.lyricInfo, true)
    }

    override fun initViews() {

    }
}