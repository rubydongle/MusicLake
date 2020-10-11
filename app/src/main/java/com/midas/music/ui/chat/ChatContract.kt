package com.midas.music.ui.chat

import com.midas.music.ui.base.BaseContract
import com.midas.music.bean.MessageInfoBean


interface ChatContract {

    interface View : BaseContract.BaseView {
        fun showHistortMessages(msgList: MutableList<MessageInfoBean>)
        fun showMessages(msgList: MutableList<MessageInfoBean>)
        fun deleteSuccessful()
    }

    interface Presenter : BaseContract.BasePresenter<View> {
        fun sendMusicMessage()
        fun loadMessages( end: String? = null)
        fun loadLocalMessages()
        fun deleteMessages()
    }
}
