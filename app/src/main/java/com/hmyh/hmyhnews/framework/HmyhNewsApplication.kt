package com.hmyh.hmyhnews.framework

import android.app.Application
import android.content.Context
import com.hmyh.hmyhnews.framework.model.impl.HmyhNewsModelImpl

class HmyhNewsApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        application = applicationContext

        HmyhNewsModelImpl.init(applicationContext)
    }

    companion object{
        lateinit var application: Context
    }

}