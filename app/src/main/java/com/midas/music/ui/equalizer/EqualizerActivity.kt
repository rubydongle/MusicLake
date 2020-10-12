package com.midas.music.ui.equalizer

//import android.app.ActionBar
import androidx.appcompat.app.ActionBar
import android.view.LayoutInflater
import com.midas.music.R
import com.midas.music.player.PlayManager
import com.midas.music.ui.base.BaseActivity
import com.midas.music.ui.base.BaseContract
import com.midas.music.ui.base.BasePresenter

class EqualizerActivity : BaseActivity<BasePresenter<BaseContract.BaseView>>() {

    override fun getLayoutResID(): Int {
        return R.layout.activity_equalizer
    }

    override fun initView() {
        val view = LayoutInflater.from(this).inflate(R.layout.switch_layout_view, null)
        val layoutParams = ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT)
        supportActionBar?.setCustomView(view, layoutParams)
        supportActionBar?.setDisplayShowCustomEnabled(true)
//        val titleSwitchBtn = view.findViewById<Switch>(R.id.title_switch_btn)
    }

    override fun initData() {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.container, EqualizerFragment.newInstance(PlayManager.getAudioSessionId()))
                .commit()
    }

    override fun initInjector() {

    }



}
