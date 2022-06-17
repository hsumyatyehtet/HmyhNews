package com.hmyh.news.framework

import android.os.Bundle

fun getBundleNewsDetail(author: String): Bundle{
    return Bundle().apply {
        putString("news_author",author)
    }
}