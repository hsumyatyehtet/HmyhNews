package com.hmyh.hmyhnews.framework.persistance.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hmyh.hmyhnews.domain.NewsListVO
import io.reactivex.Completable

@Dao
interface NewsListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNewData(newsListVO: NewsListVO): Completable

    @Query("SELECT * from new_list")
    fun getNew(): LiveData<NewsListVO>

}