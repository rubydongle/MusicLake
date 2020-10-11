package com.midas.music.ui.chat

import com.midas.music.api.playlist.PlaylistApiServiceImpl
import com.midas.music.bean.MessageInfoBean
import com.midas.music.api.net.ApiManager
import com.midas.music.api.net.RequestCallBack
import org.litepal.LitePal


/**
 * Des    :
 * Author : master.
 * Date   : 2018/9/27 .
 */
class ChatModel {


    //获取MessageInfoBean
    fun loadHistoryMessages(): MutableList<MessageInfoBean> {
        return LitePal.order("datetime asc").find(MessageInfoBean::class.java, true)
    }

    //获取MessageInfoBean
    fun deleteAllMessages(): Int {
        return LitePal.deleteAll(MessageInfoBean::class.java)
    }

    /**
     * 获取历史聊天记录
     *
     */
    fun getChatHistory(start: String? = null, end: String? = null, success: (MutableList<MessageInfoBean>?) -> Unit?, fail: (String?) -> Unit?) {
        ApiManager.request(PlaylistApiServiceImpl.playlistApiService.getChatHistory(PlaylistApiServiceImpl.token, start, end), object : RequestCallBack<MutableList<MessageInfoBean>> {
            override fun success(result: MutableList<MessageInfoBean>?) {
                success.invoke(result)
            }

            override fun error(msg: String?) {
                fail.invoke(msg)
            }
        })
    }
}