package com.hmyh.hmyhnews.framework.persistance.typeconverter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hmyh.domain.ArticleListVO

class ArticleListTypeConverter {

    @TypeConverter
    fun fromListToGson(list: List<ArticleListVO>?): String {
        return Gson().toJson(list)
    }

    @TypeConverter
    fun fromGsonToList(json: String): List<ArticleListVO>? {
        val typeToken = object : TypeToken<List<ArticleListVO>>() {}.type
        return Gson().fromJson(json, typeToken)
    }
}