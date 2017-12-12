package com.wx.dialogmaster.app

import android.app.Application
import android.os.Environment
import com.lzy.okgo.OkGo
import com.lzy.okserver.OkDownload

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        //必须的初始化
        OkGo.getInstance().init(this)
        //设置全局的下载文件夹
        OkDownload.getInstance().setFolder(Environment.getExternalStorageDirectory().path+"/okgodownfile")
        //OkDownload.getInstance().threadPool.setCorePoolSize(3) //同时下载的任务数量
    }

}