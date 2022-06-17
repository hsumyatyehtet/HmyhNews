package com.hmyh.news.framework

import android.os.Bundle

//fun getBundleNewsDetail(author: String): Bundle{
//    return Bundle().apply {
//        putString("news_author",author)
//    }
//}

fun getBundleNewsDetail(articleListVO: com.hmyh.domain.ArticleListVO): Bundle{
    return Bundle().apply {
        putParcelable("news_article",articleListVO)
    }
}