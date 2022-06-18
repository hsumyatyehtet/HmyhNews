package com.hmyh.hmyhnews.framework.persistance

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hmyh.hmyhnews.domain.NewsListVO
import com.hmyh.hmyhnews.framework.persistance.dao.NewsListDao
import com.hmyh.hmyhnews.framework.persistance.typeconverter.ArticleListTypeConverter

@Database(entities = [NewsListVO::class], version = 2, exportSchema = false)
@TypeConverters(ArticleListTypeConverter::class)
abstract class HmyhNewsDatabase : RoomDatabase() {

    abstract fun newListDao(): NewsListDao

    companion object {

        @Volatile
        private var INSTANCE: HmyhNewsDatabase? = null

        private const val DB_NAME = "HmyhNews.db"

        fun getDatabase(context: Context): HmyhNewsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                HmyhNewsDatabase::class.java, DB_NAME
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()

    }

}